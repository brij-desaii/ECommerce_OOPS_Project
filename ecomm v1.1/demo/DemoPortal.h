//Imports zone
#ifndef DEMOPORTAL_H
#define DEMOPORTAL_H
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
using namespace std;

//DemoPortal is child class of Portal
class DemoPortal : public Portal
{
private:
    //Static varible tracking the next portal ID to assign
    static int nextportalID;
    //Portal ID of each portal
    int portalID;
    //Tracking commands along with sort parameter, NotSort if not sort type function
    vector<pair<string, string> > cmds;
    //Current command whose response is being read
    int ccmd;

public:
    //DemoPortal initialiser
    DemoPortal();
    virtual void processUserCommand(string command);
    virtual void checkResponse();
};

#endif