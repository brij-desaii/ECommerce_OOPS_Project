//Imports zone
#include <iostream>
#include <complex>
#include <queue>
#include <set>
#include <unordered_set>
#include <list>
#include <chrono>
#include <random>
#include <iostream>
#include <algorithm>
#include <cmath>
#include <string>
#include <vector>
#include <map>
#include <unordered_map>
#include <stack>
#include <iomanip>
#include <fstream>
#include "../ecomm/Portal.h"
#include "DemoPortal.h"
#include <fstream>
#include <iterator>
using namespace std;

//--------------Untility Comparision Fuctions For Sorting----------------
static bool comp2(const vector<string> &vec1, const vector<string> &vec2)
{
	return (vec1[2]) < (vec2[2]);
}
static bool comp4(const vector<string> &vec1, const vector<string> &vec2)
{
	return stoi(vec1[4]) < stoi(vec2[4]);
}
//------------------------------------------------------------------------

//Initialise Portal Id, which is a static variable
int DemoPortal::nextportalID = 1;

//--------------Initailisation Fuction----------------
DemoPortal::DemoPortal()
{
	portalID = nextportalID;
	nextportalID++;
	ccmd = 0;
}
//------------------------------------------------------------------------


//------------- Fuction to Process User Commands-----------------
void DemoPortal ::processUserCommand(string command)
{
	ofstream ltf("../ecomm v1.1/PortalToPlatform.txt", std::ios_base::app);
	if (command == "Start")
	{
		//Append standard message to PortalToPlatform.txt
		ltf << portalID << " " << cmds.size() + 1 << " "
			<< "Start" << endl;

		//Append to vector, with NotSort as the argument to Identify that 
		//this is does not need to go through sorting after 
		//the receving responce
		cmds.push_back(make_pair(to_string(cmds.size() + 1), "NotSort"));
	}
	else if (command.find("List") != std::string::npos)
	{
		//-------------Slicing the command recieved-----------------
		// Varible that will store category
		string cat;

		// Varible that will store index where space was found
		int last;

		// Varible that will store the parameter to sort on
		string sortby;

		//Slicing function part 1
		for (int i = 5; i < command.length(); i++)
		{
			if (command[i] == ' ')
			{
				last = i;
				break;
			}
			cat.push_back(command[i]);
		}

		//Slicing function part 2
		for (int i = last + 1; i < command.length(); i++)
		{
			if (command[i] == ' ')
			{
				break;
			}
			sortby.push_back(command[i]);
		}
		//-------------------------------------------------------

		//Append standard message to PortalToPlatform.txt
		ltf << portalID << " " << cmds.size() + 1 << " "
			<< "List"
			<< " " << cat << endl;

		//Append to vector, with NotSort as the argument to Identify that 
		//this is does not need to go through sorting after 
		//the receving responce
		cmds.push_back(make_pair(to_string(cmds.size() + 1), sortby));
	}
	else //Buy type commands
	{
		//-------------Slicing the command recieved-----------------

		// Varible that will store item
		string item;

		// Varible that will store quantity
		int num;

		//Slicing function
		for (int i = 4; i < command.length(); i++)
		{
			if (command[i] == ' ')
			{
				num = stoi(command.substr(i + 1));
				break;
			}
			item.push_back(command[i]);
		}
		//-------------------------------------------------------
		//Append standard message to PortalToPlatform.txt
		ltf << portalID << " " << cmds.size() + 1 << " "
			<< "Buy"
			<< " " << item << " " << num << endl;
		
		//Append to vector, with NotSort as the argument to Identify that 
		//this is does not need to go through sorting after 
		//the receving responce
		cmds.push_back(make_pair(to_string(cmds.size() + 1), "NotSort"));
	}
	ltf.close();
}
//------------------------------------------------------------------------


//------------- Fuction to Check Response in PlatformToPortal.txt-----------------
void DemoPortal ::checkResponse()
{
	fstream ftl("../ecomm v1.1/PlatformToPortal.txt");
	string cmd;

	//One response is read initially
	if (!getline(ftl, cmd))
	{
		//End of file reached
		//File opend and closed in ofstream to clear the file
		ofstream file("../ecomm v1.1/PlatformToPortal.txt");
		file.close();
		return;
	}
	//Utility flag variable
	int ctrl = 0;

	//Loop to be broken when end of file is reached
	while (1)
	{
		//Check vector for knowing what type of command are we waiting for
		//In it is List command, then the below if condition is taken
		if (cmds[ccmd].second != "NotSort")
		{
			//Vector of vector to store sliced response of each entry of list.
			vector<vector<std::string> > lisst;

			//---------Slice the response and add to the lisst.-----------
			std::stringstream ss(cmd);
			std::istream_iterator<std::string> begin(ss);
			std::istream_iterator<std::string> end;
			std::vector<std::string> vstrings(begin, end);
			lisst.push_back(vstrings);
			//------------------------------------------------------------

			if (!getline(ftl, cmd))//Next response is read
			{
				//End of file reached
		        //File opend and closed in ofstream to clear the file
				ofstream file("../ecomm v1.1/PlatformToPortal.txt");
	            file.close();
				return;
			}

			//Responses read till the list ends
			while (1)
			{

				std::stringstream ss(cmd);
				std::istream_iterator<std::string> begin(ss);
				std::istream_iterator<std::string> end;
				std::vector<std::string> vstrings(begin, end);
				if ((cmds[ccmd].first) == (vstrings[1]))
				{
					lisst.push_back(vstrings);
				}
				else
				{
					break;
				}
				if (!getline(ftl, cmd))
				{
					ctrl = 1;
					break;
				}
			}

			//Sort based on name 
			if (cmds[ccmd].second == "Name")
			{
				sort(lisst.begin(), lisst.end(), comp2);
			}

			//Sort based on price
			if (cmds[ccmd].second == "Price")
			{
				sort(lisst.begin(), lisst.end(), comp4);
			}

			//Print sorted output to terminal

			for (int i = 0; i < lisst.size(); i++)
			{
				for (int j = 0; j < lisst[i].size(); j++)
					cout << lisst[i][j] << " ";
				cout << endl;
			}

			ccmd++;

			if (ctrl == 1)
			{
				//End of file reached
		        //File opend and closed in ofstream to clear the file
				ofstream file("../ecomm v1.1/PlatformToPortal.txt");
				file.close();
				return;
			}

		}
		else
		{
			cout << cmd << endl;
			ccmd++;
			if (!getline(ftl, cmd))
			{
				//End of file reached
		        //File opend and closed in ofstream to clear the file
				ofstream file("../ecomm v1.1/PlatformToPortal.txt");
				file.close();
				return;
			}
		}
	}
	
	//End of file reached
	//File opend and closed in ofstream to clear the file
	ofstream file("../ecomm v1.1/PlatformToPortal.txt");
	file.close();
	ftl.close();
}
//---------------------------------------------------------------------------------------