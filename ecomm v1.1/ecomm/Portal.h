//Imports zone
#ifndef PORTAL_H
#define PORTAL_H
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
using namespace std;

//Parent Virtual class, all methods to be overriden by child classes
class Portal
{
public:
    virtual void processUserCommand(string command)=0;
	virtual void checkResponse()=0;
};

#endif