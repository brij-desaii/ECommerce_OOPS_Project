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
static bool comp2(const vector<string> &vec1, const vector<string> &vec2)
{
	// cout<<vec1[2]<<"lol"<<endl;
	return (vec1[2]) < (vec2[2]);
	// return vec1[2].compare( vec2[2]);
}
static bool comp4(const vector<string> &vec1, const vector<string> &vec2)
{
	return stoi(vec1[4]) < stoi(vec2[4]);
}
int DemoPortal::nextportalID = 1;

DemoPortal::DemoPortal()
{
	portalID = nextportalID;
	nextportalID++;
	ccmd = 0;
}

void DemoPortal ::processUserCommand(string command)
{
	ofstream ltf("../ecomm v1.1/PortalToPlatform.txt", std::ios_base::app);
	if (command == "Start")
	{
		ltf << portalID << " " << cmds.size() + 1 << " "
			<< "Start" << endl;
		cmds.push_back(make_pair(to_string(cmds.size() + 1), "NotSort"));
	}
	else if (command.find("List") != std::string::npos)
	{
		string cat;
		int last;
		string sortby;
		for (int i = 5; i < command.length(); i++)
		{
			if (command[i] == ' ')
			{
				last = i;
				break;
			}
			cat.push_back(command[i]);
		}
		for (int i = last + 1; i < command.length(); i++)
		{
			if (command[i] == ' ')
			{
				break;
			}
			sortby.push_back(command[i]);
		}
		ltf << portalID << " " << cmds.size() + 1 << " "
			<< "List"
			<< " " << cat << endl;
		cmds.push_back(make_pair(to_string(cmds.size() + 1), sortby));
	}
	else
	{
		string item;
		int num;
		for (int i = 5; i < command.length(); i++)
		{
			if (command[i] == ' ')
			{
				num = int(command[i + 1]) - 21;
				break;
			}
			item.push_back(command[i]);
		}
		ltf << portalID << " " << cmds.size() + 1 << " "
			<< "Buy"
			<< " " << item << " " << num << endl;
		cmds.push_back(make_pair(to_string(cmds.size() + 1), "NotSort"));
	}
	ltf.close();
}
void DemoPortal ::checkResponse()
{
	fstream ftl("../ecomm v1.1/PlatformToPortal.txt");
	string cmd;
	// Use a while loop together with the getline() function to read the file line by line
	if (!getline(ftl, cmd))
	{
		// cout<<"fffffff";
		ofstream file("../ecomm v1.1/PlatformToPortal.txt");
	file.close();
		return;
	}

	while (1)
	{
		if (cmds[ccmd].second != "NotSort")
		{
			// cout<<"iusbvui";
			std::stringstream ss(cmd);
			std::istream_iterator<std::string> begin(ss);
			std::istream_iterator<std::string> end;
			std::vector<std::string> vstrings(begin, end);
			vector<vector<std::string> > lisst;
			lisst.push_back(vstrings);
			if (!getline(ftl, cmd))
			{
				ofstream file("../ecomm v1.1/PlatformToPortal.txt");
	file.close();
				// cout<<"iusbvuijonhiugy";
				return;
			}

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
					// cout<<"bfeuiyghvfcijodhfgijoshgisugvugvc hb vhuihjuhbv chgvc bghuighcfhuiohgvchuigccghughcvx ghuhjgvc ghvcv";
					break;
				}
				if (!getline(ftl, cmd))
				{
					break;
				}
			}
			// cout<<cmds[ccmd].second;
			if (cmds[ccmd].second == "Name")
			{
				// cout<<"hh+++";
				sort(lisst.begin(), lisst.end(), comp2);
			/*
			std::sort(lisst.begin(), lisst.end(),[](const std::vector<string>& a, const std::vector<string>& b) {
			return a[2] < b[2];
		});*/}
			if (cmds[ccmd].second == "Price")
			{
				// cout<<"xxxxx";
				sort(lisst.begin(), lisst.end(), comp4);
				/*
				std::sort(lisst.begin(), lisst.end(),
				[](const std::vector<string>& a, const std::vector<string>& b) {
				return stoi(a[4]) < stoi(b[4]);
			});*/
			}
			// cout<<lisst.size();
			for (int i = 0; i < lisst.size(); i++)
			{
				for (int j = 0; j < lisst[i].size(); j++)
					cout << lisst[i][j] << " ";
				cout << endl;
			}
			/*
			for(int k=0;k<lisst.size();k++){
				std::copy(lisst[k].begin(), lisst[k].end(), ostream_iterator<string>(cout<<" "));
				cout<<endl;
			}
			*/
			ccmd++;
		}
		else
		{
			cout << cmd << endl;
			ccmd++;
			// cout<<"jknjkn";
			if (!getline(ftl, cmd))
			{
				ofstream file("../ecomm v1.1/PlatformToPortal.txt");
	file.close();
				return;
			}
			// cout<<cmd;
		}
		// Close the file
	}
	ofstream file("../ecomm v1.1/PlatformToPortal.txt");
	file.close();
	ftl.close();
}