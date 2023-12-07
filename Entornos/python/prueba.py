string = 'the beautiful white moon '
white_space = ' '

lexeme = ''
for i,char in enumerate(string):
    lexeme += char # adding a char each time
    if (i+1 < len(string)): # prevents error
        if string[i+1] == white_space: # if next char == ' '
            print(lexeme)
            lexeme = ''