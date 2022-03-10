# Importing library
import numpy as np
import time
import os

# Initializing maze
grid = """#############################
###                B#########
#   ###################   # #
# ####                # # # #
# ################### # # # #
#                     # # # #
##################### # # # #
#   ##                # # # #
# # ## ### ## ######### # # #
# #    #   ####         # # #
# # ## ################ # # #
### ##             #### # # #
### ############## ## # # # #
###             ##    # # # #
###### ######## ####### # # #
###### ####             #   #
#A     ######################
#############################
"""

grid = """################################################################################
#A                                                                  #          #
################################################################### # ######## #
#                                                           ####### # ##    ## #
# ######################################################### #       # ## ## ## #
# # #B                                                    # ####### # ## ## ## #
# # ##################################################### #         # ## #  ## #
# #                                                       ######### # ## ## ## #
# ####################################################### # #       # ## ## ## #
# #                                                         # ##### # ## ## ## #
# # ####################################################### # # ### # ## ## ## #
# # #        #########################                      # #     # ## ## ## #
# # # ######                         ######################## ##### # ## ## ## #
# # # ## ### ####################### # ##     ###     ##    ##    # # ## ## ## #
# # #  #     #                       #    ###     ###    ##    #### # ## ## ## #
# # ## ############################# # ############################ # ## ## ## #
# # ## #                        ###  #                            # # ## ## ## #
# # ## # ########### ############   ## ############################## ## ## ## #
# #    #           # #            ####                                #  ###   #
# ################ # ####### ######### ##############################   #### # #
#                # # #                                            # ########## #
######## ######### # ############################################## #          #
#                  #                                                  ### ######
################################################################################
"""

# Output function
def output_maze(grid, is_solving):
	print()
	for i in grid:
		for j in i:
			if j == "A":
				if is_solving:
					print("\033[1;31mA\033[0;0m", end = "")
				else:
					print("\033[5;31mA\033[0;0m", end = "")
			elif j == "B":
				if is_solving:
					print("\033[5;32mB\033[0;0m", end = "")
				else:
					print("\033[1;32mB\033[0;0m", end = "")
			elif j == "*":
				print("\033[1;36m*\033[0;0m", end = "")
			elif j == "#":
				print("\033[1;40;40m \033[0;0m", end = "")
			else:
				print(" ", end = "")
		print()

# Clearing output function
def cls():
    os.system('cls' if os.name=='nt' else 'clear')

# Parsing input
col_length = 0
while grid[col_length] != "\n":
	col_length += 1
row_length = len(grid) % col_length
maze = [[""]*col_length for i in range(row_length)]
count = 0
col_length += 1
for i in range(len(grid)):
	if grid[i] != "\n":
		maze[count // col_length][count % col_length] = grid[i]
	count += 1
output_maze(maze, False)

# Creating adjacency list
adjacency = {}
start = [0,0]
end = [0,0]

for i in range(len(maze)):
	for j in range(len(maze[i])):
		if maze[i][j] == " " or maze[i][j] == "A":
			if maze[i][j] == "A":
				start = [i,j]
			index = f"{i},{j}"
			adjacency[index] = []
			try:
				if i-1 >= 0 and (maze[i-1][j] == " " or maze[i-1][j] == "B"):
					adjacency[index].append([i-1,j])
			except: pass
			try:
				if j-1 >= 0 and (maze[i][j-1] == " " or maze[i][j-1] == "B"):
					adjacency[index].append([i,j-1])
			except: pass
			try:
				if maze[i+1][j] == " " or maze[i+1][j] == "B":
					adjacency[index].append([i+1,j])
			except: pass
			try:
				if maze[i][j+1] == " " or maze[i][j+1] == "B":
					adjacency[index].append([i,j+1])
			except: pass
		elif maze[i][j] == "B":
			end = [i,j]

# Defining search algorithm
def bfs(start, end, map):
  # True -->  End result found
  # False --> End result not found
  visited = []
  queue = [(start,[start])]

  while len(queue) > 0:
    vertex, path = queue.pop(0)
    visited.append(vertex)
    index = f"{vertex[0]},{vertex[1]}"
    for connection in map[index]:
    	if [connection[0], connection[1]] == end:
    		return path + [end]
    	else:
    		if connection not in visited:
    			visited.append(connection)
    			queue.append((connection, path + [connection]))
  return False

# Searching for path and completing maze
input("")
search_result = bfs(start, end, adjacency)
if search_result == False:
	print("\n\nSorry, no path could be found. :(")
else:
	print("\n\n\n\nSOLUTION FOUND!")
	for i in search_result[1:-1]:
		cls()
		maze[i[0]][i[1]] = "*"
		output_maze(maze, True)
		print()
		time.sleep(0.05)
