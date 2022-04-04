"""
Word Search Solver
"""

# Finding Start Points for a Given Word
def find_start_points(word, search_array):
	start_points = []
	for i in range(len(search_array)):
		for j in range(len(search_array[i])):
			if search_array[i][j] == word[0]:
				start_points.append([i,j,1,0,0])
	return start_points

# Finding Adjacent Points for a Given Location
def adjacent_points(word, i, j, index, movement, search_array):
	if movement != [0,0]:
		try:
			if (i + movement[0]) > -1 and (j + movement[1]) > -1:
				if search_array[i + movement[0]][j + movement[1]] == word[index]:
					return [[i + movement[0], j + movement[1], index+1, movement[0], movement[1]]]
		except:
			pass
		return []

	connected = []
	try:
		if search_array[i-1][j] == word[index] and i-1 > -1:
			connected.append([i-1,j,index+1,0,0])
	except: pass
	try:
		if search_array[i+1][j] == word[index]:
			connected.append([i+1,j,index+1,0,0])
	except: pass
	try:
		if search_array[i][j-1] == word[index] and j-1 > -1:
			connected.append([i,j-1,index+1,0,0])
	except: pass
	try:
		if search_array[i][j+1] == word[index]:
			connected.append([i,j+1,index+1,0,0])
	except: pass
	try:
		if search_array[i-1][j-1] == word[index] and i-1 > -1 and j-1 > -1:
			connected.append([i-1,j-1,index+1,0,0])
	except: pass
	try:
		if search_array[i-1][j+1] == word[index] and i-1 > -1:
			connected.append([i-1,j+1,index+1,0,0])
	except: pass
	try:
		if search_array[i+1][j-1] == word[index] and j-1 > -1:
			connected.append([i+1,j-1,index+1,0,0])
	except: pass
	try:
		if search_array[i+1][j+1] == word[index]:
			connected.append([i+1,j+1,index+1,0,0])
	except: pass
	return connected

def find_word(word, search_array):
	start_values = find_start_points(word, search_array)
	for start in start_values:
		visited = []
		index = 1
		queue = []
		queue.append(start)
		visited.append(start)

		while len(queue) > 0:
			place = queue.pop(0)
			index = place[2]
			for connection in adjacent_points(word, place[0], place[1], index, [place[3],place[4]], search_array):
				if connection[2] == len(word):
					x = connection[0] - ((connection[2] - 1) * connection[3])
					y = connection[1] - ((connection[2] - 1) * connection[4])
					return connection[0],connection[1],x,y
				else:
					if connection not in visited:
						connection[3] = connection[0]-place[0]
						connection[4] = connection[1]-place[1]
						visited.append(connection)
						queue.append(connection)
	return None
                        

