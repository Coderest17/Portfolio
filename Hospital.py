# Importing libraries
import numpy as np
import random
import time
import os

# Initializing grid
size = [10,20]
grid = [["0"]*size[1] for i in range(size[0])]

# Declaring map settings
num_hospitals = 5
num_famillies = 50

# Initializing arrays to store family and hospital locations
family_locations = []
hospital_locations = []

# Generating family locations
for i in range(num_famillies):

	# Getting random coordinate for placement
	x = random.randint(0,len(grid)-1)
	y = random.randint(0,len(grid[0])-1)

	# Checking coordinate
	while grid[x][y] != "0":

		# Regenerating placement coordinate
		x = random.randint(0,len(grid)-1)
		y = random.randint(0,len(grid[0])-1)

	# Initializing family
	grid[x][y] = "F"

	# Storing family's location in array
	family_locations.append([x,y])

# Generating hospital locations
for i in range(num_hospitals):

	# Getting random coordinate for placement
	x = random.randint(0,len(grid)-1)
	y = random.randint(0,len(grid[0])-1)

	# Checking coordinate
	while grid[x][y] != "0":

		# Regenerating placement coordinate
		x = random.randint(0,len(grid)-1)
		y = random.randint(0,len(grid[0])-1)

	# Initializing hospital
	grid[x][y] = "H"

	# Storing hospital's location in array
	hospital_locations.append([x,y])

# Clearing output function
def cls():
    os.system('cls' if os.name=='nt' else 'clear')

# Distance between two points
def distance(point_1, point_2):
	return abs(point_2[1] - point_1[1]) + abs(point_2[0] - point_1[0])

# Finding closest hospital from given family location
def closest_hospital(family_coordinate, hospital_coordinates):
	distance_costs = []
	for i in hospital_coordinates:
		distance_costs.append(distance(family_coordinate,i))
	return min(distance_costs)

# Calculating current cost of all famillies
def current_cost(family_coordinates, hospital_coordinates):
	total_cost = 0
	for family in family_coordinates:
		total_cost += closest_hospital(family, hospital_coordinates)
	return total_cost

# PRINT FUNCTION (FOR ASTHETICS)
def output_map(map):
	for i in range(len(map)):
		for place in map[i]:
			if place == "H":
				print("\033[1;32mH\033[0;0m", end = " ")
			elif place == "F":
				print("\033[3;34mF\033[0;0m", end = " ")
			else:
				print(" ", end = " ")
		print("")

# Outputting current cost
present_cost = current_cost(family_locations, hospital_locations)
initial_cost = present_cost
print(f"\n\nINTIAL COST (COST 0): {present_cost}")
#print(np.matrix(grid))
output_map(grid)
time.sleep(5)

# Possible changes for hospital placements
def possible(map, hospital_coordinates):
	possible_moves = []
	for i in hospital_coordinates:
		try:
			if map[i[0]-1][i[1]] == "0":
				possible_moves.append([i,[i[0]-1,i[1]]])
		except: pass

		try:
			if map[i[0]+1][i[1]] == "0":
				possible_moves.append([i,[i[0]+1,i[1]]])
		except: pass

		try:
			if map[i[0]][i[1]-1] == "0":
				possible_moves.append([i,[i[0],i[1]-1]])
		except: pass

		try:
			if map[i[0]][i[1]+1] == "0":
				possible_moves.append([i,[i[0],i[1]+1]])
		except: pass

		# DIAGONALS
		#"""
		try:
			if map[i[0]-1][i[1]+1] == "0":
				possible_moves.append([i,[i[0]-1,i[1]+1]])
		except: pass

		try:
			if map[i[0]+1][i[1]-1] == "0":
				possible_moves.append([i,[i[0]+1,i[1]-1]])
		except: pass

		try:
			if map[i[0]-1][i[1]-1] == "0":
				possible_moves.append([i,[i[0]-1,i[1]-1]])
		except: pass

		try:
			if map[i[0]+1][i[1]+1] == "0":
				possible_moves.append([i,[i[0]+1,i[1]+1]])
		except: pass
		#"""

	return possible_moves

best_increase = 0.5
while best_increase > 0:

	best_increase = 0
	best_change = []
	final_hospital_locations = []

	for change in possible(grid, hospital_locations):
		modified_hospital_locations = [i for i in hospital_locations]
		modified_hospital_locations.remove(change[0])
		modified_hospital_locations.append(change[1])

		increase = present_cost - current_cost(family_locations, modified_hospital_locations)
		if best_increase < increase:
			best_increase = increase
			best_change = change
			final_hospital_locations = [i for i in modified_hospital_locations]

	if len(best_change) != 0:
		grid[best_change[0][0]][best_change[0][1]] = "0"
		grid[best_change[1][0]][best_change[1][1]] = "H"
		hospital_locations = [i for i in final_hospital_locations]

	present_cost = current_cost(family_locations, hospital_locations)
	cls()
	print(f"\nFound Better Neighbour --> COST: {present_cost}")
	output_map(grid)
	time.sleep(1)

print(f"\nInitial Cost: {initial_cost} vs. Present Cost: {present_cost}\n")
