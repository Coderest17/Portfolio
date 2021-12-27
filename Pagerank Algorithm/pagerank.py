import os
import random
import re
import sys

DAMPING = 0.85
SAMPLES = 10000


def main():
    if len(sys.argv) != 2:
        sys.exit("Usage: python pagerank.py corpus")
    corpus = crawl(sys.argv[1])
    ranks = sample_pagerank(corpus, DAMPING, SAMPLES)
    print(f"PageRank Results from Sampling (n = {SAMPLES})")
    for page in sorted(ranks):
        print(f"  {page}: {ranks[page]:.4f}")
    ranks = iterate_pagerank(corpus, DAMPING)
    print(f"PageRank Results from Iteration")
    for page in sorted(ranks):
        print(f"  {page}: {ranks[page]:.4f}")


def crawl(directory):
    """
    Parse a directory of HTML pages and check for links to other pages.
    Return a dictionary where each key is a page, and values are
    a list of all other pages in the corpus that are linked to by the page.
    """
    pages = dict()

    # Extract all links from HTML files
    for filename in os.listdir(directory):
        if not filename.endswith(".html"):
            continue
        with open(os.path.join(directory, filename)) as f:
            contents = f.read()
            links = re.findall(r"<a\s+(?:[^>]*?)href=\"([^\"]*)\"", contents)
            pages[filename] = set(links) - {filename}

    # Only include links to other pages in the corpus
    for filename in pages:
        pages[filename] = set(
            link for link in pages[filename]
            if link in pages
        )

    return pages


def transition_model(corpus, page, damping_factor):
    """
    Return a probability distribution over which page to visit next,
    given a current page.

    With probability `damping_factor`, choose a link at random
    linked to by `page`. With probability `1 - damping_factor`, choose
    a link at random chosen from all pages in the corpus.
    """
    temp = {}
    for i in corpus.keys():
        temp[i] = 0
    if len(corpus[page]) == 0:
        for i in corpus.keys():
            temp[i] = round(1/len(corpus.keys()),4)
    else:
        for i in corpus[page]:
            temp[i] += round(damping_factor/len(corpus[page]),4)
        for i in corpus.keys():
            temp[i] += round((1-damping_factor)/len(corpus),4)
    return temp


def sample_pagerank(corpus, damping_factor, n):
    """
    Return PageRank values for each page by sampling `n` pages
    according to transition model, starting with a page at random.

    Return a dictionary where keys are page names, and values are
    their estimated PageRank value (a value between 0 and 1). All
    PageRank values should sum to 1.
    """
    temp = {}
    for i in corpus.keys():
        temp[i] = 0
    page_index = random.randint(0,len(corpus.keys())-1)
    page_start = list(corpus.keys())[page_index]
    temp[list(corpus.keys())[page_index]] += 1
    sample = transition_model(corpus, page_start, damping_factor)
    for i in range(n-1):
        val = random.randint(1, 1000)
        probs = []
        count = 0
        for x in sample.values():
            if count == 0:
                probs.append(x*1000)
                count += 1
            else:
                probs.append(x*1000 + probs[-1])
        page_num = 0
        for i in probs:
            if val <= i:
                sample = transition_model(corpus, list(sample.keys())[page_num], damping_factor)
                temp[list(sample.keys())[page_num]] += 1
                break
            page_num += 1
    for i in temp.keys():
        temp[i] /= n 
    return temp

def iterate_pagerank(corpus, damping_factor):
    """
    Return PageRank values for each page by iteratively updating
    PageRank values until convergence.

    Return a dictionary where keys are page names, and values are
    their estimated PageRank value (a value between 0 and 1). All
    PageRank values should sum to 1.
    """
    temp = {}
    num_pages = len(corpus.keys())
    for i in corpus.keys():
        temp[i] = round(1/num_pages,3)

    diff = 1
    while diff >= 0.001:
        for i in corpus.keys():
            a = (1-damping_factor)/num_pages
            b = 0
            vals = []
            empty = []
            c = False
            for x in range(len(list(corpus.values()))):
                if list(corpus.values())[x] == None:
                    empty.append(x)
                    c = True
                elif i in list(corpus.values())[x]:
                    vals.append(x)
            for y in vals:
                b += (list(temp.values())[y])/(len(list(corpus.values())[y]))
            if c == True:
                for q in empty:
                    b += (list(temp.values())[q])/num_pages
            b *= damping_factor
            c = a + b
            diff = temp[i] - c
            if diff < 0:
                diff *= -1
            temp[i] = round(c,4)
            
    total = 0
    for a in temp.keys():
        total += temp[a]
    if round(total,4) != 1.0000:
        total = 1.0000/total
        for i in temp.keys():
            temp[i] *= total
    for z in temp.keys():
        temp[z] = round(temp[z],4)
    return temp

if __name__ == "__main__":
    main()
