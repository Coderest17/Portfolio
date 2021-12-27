import numpy as np
import os
import sys
import smtplib

"""
SAMPLE INPUT:
- Psychological Pre-screen Interview Report, by Dr. XYZ, dated October 28, 2020.pdf
- Bilateral Shoulder Ultrasound, by Dr. XYZ, dated August 16, 2020.pdf
- Psychology Report, by Dr. XYZ, dated May 28, 2021.pdf
- Physician Addendum - Insurer Examination, by Dr. XYZ, dated April 1, 2021.pdf
- Left Shoulder Radiographs and Ultrasound, by Dr. XYZ, dated December 21, 2020.pdf
- Multidisciplinary Assessment Report, by Dr. XYZ, dated December 2, 2020.pdf
- Physician Assessment Report, by Dr. XYZ, dated December 2, 2020.pdf
- MRI Reports, dated March 1, 2021.pdf
- Physician Paper Review - Insurer Examination, by Dr. XYZ, dated April 7, 2021.pdf
- Orthopaedic Report, by Dr. XYZ, dated April 28, 2021.pdf

SAMPLE OUTPUT:
- Bilateral Shoulder Ultrasound, by Dr. XYZ, dated August 16, 2020
- Psychological Pre-screen Interview Report, by Dr. XYZ, dated October 28, 2020
- Multidisciplinary Assessment Report, by Dr. XYZ, dated December 2, 2020
- Physician Assessment Report, by Dr. XYZ, dated December 2, 2020
- Left Shoulder Radiographs and Ultrasound, by Dr. XYZ, dated December 21, 2020
- MRI Reports, dated March 1, 2021
- Physician Addendum - Insurer Examination, by Dr. XYZ, dated April 1, 2021
- Physician Paper Review - Insurer Examination, by Dr. XYZ, dated April 7, 2021
- Orthopaedic Report, by Dr. XYZ, dated April 28, 2021
- Psychology Report, by Dr. XYZ, dated May 28, 2021
"""

def load_data(data_dir):
    files = []
    final = []
    for file in os.listdir(data_dir):
        if file == ".DS_Store":
            pass
        else:
            f = file.split(".")[:-1]
            temp = ""
            for i in f:
                temp += i
                temp += "."
            temp = temp[:-1]
            files.append(temp)

    replace = {"Jan":"January","Feb":"February","Mar":"March","Apr":"April",
    "May":"May", "Jun":"June","Jul":"July","Aug":"August","Sep":"September",
    "Oct":"October","Nov":"November","Dec":"December", 
    "CN&R":"Clinical Notes and Records", "CNR":"Clinical Notes and Records",
    "CNRs":"Clinical Notes and Records", "IE":"Insurer Examination", "PSYCH":"Psychological",
    "Psych":"Psychological"}

    for test in files:
        temp = ""
        words = test.split()
        for i in range(len(words)):
            if words[i] in replace.keys():
                words[i] = replace[words[i]]
        for i in words:
                temp += i
                temp += " "
        temp = temp[:-1]
        final.append(temp)
        
    return final

def send_mail(text):
    server = smtplib.SMTP('smtp.gmail.com', 587)
    server.ehlo()
    server.starttls()
    server.ehlo()

    server.login("YOUR_EMAIL", "EMAIL_LOGIN_TOKEN")
    subject = "Your List of Files:"
    msg = f"Subject: {subject}\n\n{text}"
    server.sendmail("YOUR_EMAIL", "RECEIVING_EMAIL", msg)
    print("Email Sent Successfully!")
    server.quit()

def digit_count(s):
    c = 0
    nums = ["0","1","2","3","4","5","6","7","8","9"]
    for i in s:
        if i in nums:
            c += 1
        else:
            return 0
    return c

def find_year(s):
    length = len(s)-4
    count = 0
    start = length
    end = 0
    while count != length+1:
        try:
            if end == 0:
                year = int(s[start:])
                if digit_count(str(year)) == 4:
                    return year
            else:
                year = int(s[start:end])
                if digit_count(str(year)) == 4:
                    return year
        except: pass
        count += 1
        start -= 1
        end -= 1
    raise ValueError("Not Possible")


def find_month(s):
    months = {"Jan":"January","Feb":"February","Mar":"March","Apr":"April","May":"May",
             "Jun":"June","Jul":"July","Aug":"August","Sep":"September","Sept":"September","Oct":"October",
             "Nov":"November","Dec":"December"}
    
    length = len(s)-4
    count = 0
    start = length
    end = 0
    while count != length+1:
        if end == 0:
            for key in months.keys():
                if key in s[start:]:
                    return months[key]
        else:
            for key in months.keys():
                if key in s[start:end]:
                    return months[key]
        count += 1
        start -= 1
        end -= 1
    raise ValueError("Not Possible")

def not_year(s, start, end = -1.7):
    nums = ["0","1","2","3","4","5","6","7","8","9"]
    if end == -1.7:
        if s[start-1] in nums:
            return False
    else:
        if s[start-1] in nums or s[end] in nums:
            return False
    return True

def not_year_single(s, start):
    nums = ["0","1","2","3","4","5","6","7","8","9"]
    if s[start-1] in nums or s[start+1] in nums:
        return False
    return True

def find_day(s):
    two_index = -1.7
    one_index = -1.7
    
    length1 = len(s)-2
    count1 = 0
    start1 = length1
    end1 = 0
    
    while count1 != length1+1:
        try:
            if end1 == 0:
                day = int(s[start1:])
                if digit_count(str(day)) == 2 and not_year(s, start1):
                    two_index = start1
                    break
            else:
                day = int(s[start1:end1])
                if digit_count(str(day)) == 2 and not_year(s, start1, end1):
                    two_index = start1
                    break
                
        except: pass
        count1 += 1
        start1 -= 1
        end1 -= 1
    
    length2 = len(s)-1
    count2 = 0
    start2 = length2
    while count2 != length2+1:
        try:
            day = int(s[start2])
            if not_year_single(s, start2):
                one_index = start2
                break
        except: pass
        count2 += 1
        start2 -= 1
    
    if one_index != -1.7 and two_index != -1.7:
        if one_index > two_index:
            return int(s[one_index])
        else:
            return int(s[two_index:two_index+2])
    elif one_index != -1.7:
        return int(s[one_index])
    elif two_index != -1.7:
        return int(s[two_index:two_index+2])
    else:
        raise ValueError("Not Possible")


def earlier(date1, date2):
    #format: [day, month, year]
    if date1[2] < date2[2]:
        return "A"
    elif date1[2] > date2[2]:
        return "B"
    else:
        if date1[1] < date2[1]:
            return "A"
        elif date1[1] > date2[1]:
            return "B"
        else:
            if date1[0] < date2[0]:
                return "A"
            elif date1[0] > date2[0]:
                return "B"
            else:
                return "A"

def organize_by_date(file_list):
    month_vals = {"January":0,"February":1,"March":2,"April":3,"May":4,
             "June":5,"July":6,"August":7,"September":8,"October":9,
             "November":10,"December":11}
    anchor = 0
    while anchor < len(file_list) - 1:
        for i in range(anchor,len(file_list) - 1):
            try:
                year_prev = find_year(file_list[anchor])
                month_prev = month_vals[find_month(file_list[anchor])]
                day_prev = find_day(file_list[anchor])
                year_next = find_year(file_list[i+1])
                month_next = month_vals[find_month(file_list[i+1])]
                day_next = find_day(file_list[i+1])
                date1 = [day_prev, month_prev, year_prev]
                date2 = [day_next, month_next, year_next]
                if earlier(date1, date2) == "B":
                    file_list[anchor], file_list[i+1] = file_list[i+1], file_list[anchor]
            except: pass
        anchor += 1

if len(sys.argv) != 2:
    sys.exit("Usage: python file_organizer.py data_directory")
files = load_data(sys.argv[1])
organize_by_date(files)
formatted_text = "Here are your files: \n"

for i in files:
    formatted_text += f"{i}\n"
print(f"\n{formatted_text}")
send_mail(formatted_text)