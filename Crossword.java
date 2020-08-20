/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funprojects;

/**
 *
 * @author jasonfujii
 */
import java.util.ArrayList;
public class Crossword {
    private int dim;
    private ArrayList<String> list;
    public char[][] board;
    
    public Crossword(int d, ArrayList<String> l)
    {
        dim = d;
        list = l;
        board = new char[d][d];
        
    }
    
    //fitWord takes a word and fills it into a 2D array, char by char. 
    //it will find a random cell to start the word and will check all directions 
    //around to see if the word fits. If it does, fitWord() places the word and returns true. 
    //If not, it returns false
    public boolean fitWord(String s)
    {
        int len = s.length();
        int place = (int)(Math.random()*dim*dim);
        for(int o = 0; o < board.length; o++)
        {
            for(int i = 0; i < board[0].length; i++)
            {
                if(place == 0)
                {
                    //this is where you have to start!
                    //What you know:
                    //board[][]. now use that to check the surroundings.
                    //call surround(o,i);
                    surround(o,i,s);
                    place--;
                }
                else
                    place--;
            }
        }
        return false;
    }
    
    public void surround(int o, int i, String s)
    {
        boolean done = false;
        int out = o;
        int in = i;
        //Math.random
        int num = (int)(Math.random()*8);
        //when counter > 7, 
        int counter = 0;
        //check if it's a valid space and not taken/outside the board
        while(!done && counter < 8)
        {
            o = out; i = in;
            if(num % 8 == 0)
            {
                if(checkSpace(o--, i--, 0, s.length()) == s.length())
                    //fill out the space
                {
                    for(int len = 0; len < s.length(); len++)
                    {
                        board[out][in] = s.charAt(len);
                        out--; in--;
                    }
                    done = true;
                    break;
                }
                else
                {
                    num++;
                    counter++;
                }
            }
            else if(num % 8 == 1)
            {
                if(checkSpace(o--, i, 1, s.length()) == s.length())
                {
                    //fill out space
                    for(int len = 0; len < s.length(); len++)
                    {
                        board[out][in] = s.charAt(len);
                        out--;
                    }
                    //break out of while loop
                    done = true;
                    break;
                }
                else
                {
                    num++; counter++;
                }
            }
            else if(num % 8 == 2)
            {
                if(checkSpace(o--, i++, 2, s.length()) == s.length())
                    //fill out the space
                {
                    for(int len = 0; len < s.length(); len++)
                    {
                        board[out][in] = s.charAt(len);
                        out--; in++;
                    }
                    done = true;
                    break;
                }
                else
                {
                    num++;
                    counter++;
                }
            }
            else if(num % 8 == 3)
            {
                if(checkSpace(o, i++, 3, s.length()) == s.length())
                    //fill out the space
                {
                    for(int len = 0; len < s.length(); len++)
                    {
                        board[out][in] = s.charAt(len);
                        in++;
                    }
                    done = true;
                    break;
                }
                else
                {
                    num++;
                    counter++;
                }
            }
            else if(num % 8 == 4)
            {
                if(checkSpace(o++, i++, 4, s.length()) == s.length())
                    //fill out the space
                {
                    for(int len = 0; len < s.length(); len++)
                    {
                        board[out][in] = s.charAt(len);
                        out++; in++;
                    }
                    done = true;
                    break;
                }
                else
                {
                    num++;
                    counter++;
                }
            }
            else if(num % 8 == 5)
            {
                if(checkSpace(o++, i, 5, s.length()) == s.length())
                    //fill out the space
                {
                    for(int len = 0; len < s.length(); len++)
                    {
                        board[out][in] = s.charAt(len);
                        out++;
                    }
                    done = true;
                    break;
                }
                else
                {
                    num++;
                    counter++;
                }
            }
            else if(num % 8 == 6)
            {
                if(checkSpace(o++, i--, 6, s.length()) == s.length())
                    //fill out the space
                {
                    for(int len = 0; len < s.length(); len++)
                    {
                        board[out][in] = s.charAt(len);
                        out++; in--;
                    }
                    done = true;
                    break;
                }
                else
                {
                    num++;
                    counter++;
                }
            }
            else if(num % 8 == 7)
            {
                if(checkSpace(o, i--, 7, s.length()) == s.length())
                    //fill out the space
                {
                    for(int len = 0; len < s.length(); len++)
                    {
                        board[out][in] = s.charAt(len);
                        in--;
                    }
                    done = true;
                    break;
                }
                else
                {
                    num++;
                    counter++;
                }
            }
        }     
    }
    
    //checkSpace is a recursive method that checks to see if the next space in a 
    //given direction is available. If it is, return 1 + checkSpace(location + 1)
    //if not, return 0. At the end, if there is enough space for the word,
    //checkSpace() will return a number equal to the length of the word
    public int checkSpace(int o, int i, int dir, int len)
    {
        //throw an exception if there's an out of bounds error
        //base case: the location != 0 || location DNE
        if(o >= board.length || o < 0 || i >= board[0].length || i < 0)
        {
            return 0;
        }
        else if(len == 0)
            return 0;
        else if(board[o][i] != 0)
            return 0;
        else
        {
            if(dir == 0)
                return 1 + checkSpace(--o, --i, dir, --len);
            else if(dir == 1)
                return 1 + checkSpace(--o, i, dir, --len);
            else if(dir == 2)
                return 1 + checkSpace(--o, ++i, dir, --len);
            else if(dir == 3)
                return 1 + checkSpace(o, ++i, dir, --len);
            else if(dir == 4)
                return 1 + checkSpace(++o, ++i, dir, --len);
            else if(dir == 5)
                return 1 + checkSpace(++o, i, dir, --len);
            else if(dir == 6)
                return 1 + checkSpace(++o, --i, dir, --len);
            else if(dir == 7)
                return 1 + checkSpace(o, --i, dir, --len);
            else
                return 0;
        }
    }
    
    
    //fits() will take a location in board and see if the word that is given to fitWord
    //fits in the spot that it has been randomly given. 
    //Checks to see if the 8 spaces surrounding the location will house the entire word
    //if it does, fits() enters the word and returns true. if not, it returns false.
    private boolean fits()
    {
         
        return false;
    }
    
    //fill() fills the unused cells with a random char and fills the 2D array to 
    //completion
    public void fill()
    {
        for(int o = 0; o < board.length; o++)
        {
            for(int i = 0; i < board[o].length; i++)
            {
                if(board[o][i] == 0) 
                {
                    int n = (int)((Math.random()*26) + 97);
                    char letter = (char)n;
                    board[o][i] = letter;
                }
            }
        }
    }
    
    public void printBoard()
    {
        for(int o = 0; o < board.length; o++)
        {
            for(int i = 0; i < board[o].length; i++)
            {
                if(i != board[o].length - 1)
                    System.out.print(board[o][i] + " ");
                else
                    System.out.println(board[o][i]);
            }
        }
    }
    
    private void sop(char s)
    {
        System.out.println(s);
    }
}
