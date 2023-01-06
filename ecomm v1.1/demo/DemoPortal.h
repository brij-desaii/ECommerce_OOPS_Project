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

class DemoPortal : public Portal
{
private:
    static int nextportalID;
    int portalID;
    vector<pair<string, string>> cmds;
    int ccmd;

public:
    DemoPortal();
    virtual void processUserCommand(string command);
    virtual void checkResponse();
};

#endif