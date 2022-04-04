import pygame
import sys
import time
import wordsearch as ws

pygame.init()

# Word Search Data
#"""
lines = ["BEHCLETUSRLB", "NMAEAPMARGHO", "ELAMESEENYTB", "DLMRMKRUSTYW", "WANSGRRGRTRO", 
"INOGSEBLRACH", "LNSSGEWUMPMS", "LYLHHRLHRIRE", "IAEEPYEMINGD", "ESNAELTMAASI", "EIONPAAROTES", 
"ELREPULREHLA", "PAWHSOTTUBWS", "BRRESREHTIMS"]
words = ["HOMER", "MARGE", "BART", "LISA", "KRUSTY", "BARNEY", "GRAMPA", "MOE", "APU", "CLETUS",
"MRBURNS", "OTTO", "NELSON", "NED", "SELMA", "PATTY", "RALPH", "WILLIE", "SMITHERS", "LENNY", "CARL"]
#"""
"""
lines = ["RACOMETEMNRXDN", "TDONNAJOUSEOIT", "AJESSENAIDNPNL", "DEDANCINGYAHKR", "SNOCSSTEPHANIE", 
"EPDITSTNSNRANV", "NSYLYTACTSNSAY", "TPIKDENIKKYCCE", "AOMMEVSSDYSDCN", "NRKEMEJUGEAREA", "NTIEOTHMEOILBL",
"ESMCCLBCMJDLEA", "ROMYMICHELLERX", "VVYCCCDIVICKYN"]
words = ["NIKKY", "COMEDY", "SPORTS", "TANNER", "STEPHANIE", "JESSE", "KIMMY", "DONNAJO", "VICKY", "REBECCA",
"DANNY", "JOEY", "COMET", "STEVE", "ALEX", "MICHELLE", "MUSIC", "DANCING"]
"""
"""
lines = ["BACDEFJHIJ", "EANDEFJEIJ", "PBNAEFJLIJ", "ABCANFJPIJ", "ABCDEAJPIJ", "ABCDEFBAIJ"]
words = ["APPLE", "BANANA", "CARROT", "GRAPES"]
"""

size = width, height = 80*len(lines[0]), 50*len(lines)

# Colors
black = (0, 0, 0)
white = (255, 255, 255)
green = (3, 252, 144)

screen = pygame.display.set_mode(size)
mediumFont = pygame.font.Font("OpenSans-Regular.ttf", 25)
solved = False

while True:

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            sys.exit()

    screen.fill(black)

    # Draw game board
    tile_size = 40
    tile_origin = (width / 3 - (len(lines[0])/2 * tile_size),
                       height / 2.3 - (len(lines)/2 * tile_size))
    tiles = []
    for i in range(len(lines)):
        row = []
        for j in range(len(lines[0])):
            text = mediumFont.render(lines[i][j], True, white)
            textRect = text.get_rect()
            textRect.center = (tile_origin[0] + j * tile_size + tile_size/2, 
                tile_origin[1] + i * tile_size + tile_size/2)
            screen.blit(text, textRect)

            rect = pygame.Rect(
                tile_origin[0] + j * tile_size,
                tile_origin[1] + i * tile_size,
                tile_size, tile_size
            )
            pygame.draw.rect(screen, white, rect, len(lines[0]))
            row.append(rect)
        tiles.append(row)

    if len(words) > len(lines):
        r = len(lines)
    else:
        r = len(words)

    for i in range(r):
        text = mediumFont.render(words[i], True, white)
        textRect = text.get_rect()
        textRect.center = (len(lines[0])*tile_size + 200, 
            tile_origin[1] + i * tile_size + tile_size/2)
        screen.blit(text, textRect)

    if r == len(lines):
        for i in range(len(lines), len(words)):
            text = mediumFont.render(words[i], True, white)
            textRect = text.get_rect()
            textRect.center = (len(lines[0])*tile_size + 350, 
                tile_origin[1] + (i-len(lines)) * tile_size + tile_size/2)
            screen.blit(text, textRect)

    if solved == False:
        pygame.display.flip()
        time.sleep(5)

    if solved == False:
        for i in range(r):        
            try:
                x1,y1,x2,y2 = ws.find_word(words[i], lines)
                pygame.draw.line(screen, green, (tile_origin[0] + y1 * tile_size + tile_size/2, 
                    tile_origin[1] + x1 * tile_size + tile_size/2), 
                    (tile_origin[0] + y2 * tile_size + tile_size/2, 
                    tile_origin[1] + x2 * tile_size + tile_size/2), 
                    width=5)

                pygame.draw.line(screen, green, (len(lines[0])*tile_size + 150, 
                    tile_origin[1] + i * tile_size + tile_size/2), 
                    (len(lines[0])*tile_size + 250, 
                    tile_origin[1] + i * tile_size + tile_size/2), 
                    width=5)
                pygame.display.flip()
                time.sleep(1)
            except: pass
        
        if r == len(lines):
            for i in range(len(lines), len(words)):
                try:
                    x1,y1,x2,y2 = ws.find_word(words[i], lines)
                    pygame.draw.line(screen, green, (tile_origin[0] + y1 * tile_size + tile_size/2, 
                        tile_origin[1] + x1 * tile_size + tile_size/2), 
                        (tile_origin[0] + y2 * tile_size + tile_size/2, 
                        tile_origin[1] + x2 * tile_size + tile_size/2), 
                        width=5)

                    pygame.draw.line(screen, green, (len(lines[0])*tile_size + 300, 
                        tile_origin[1] + (i-len(lines)) * tile_size + tile_size/2), 
                        (len(lines[0])*tile_size + 400, 
                        tile_origin[1] + (i-len(lines)) * tile_size + tile_size/2), 
                        width=5)
                    pygame.display.flip()
                    time.sleep(1)
                except: pass

    solved = True



