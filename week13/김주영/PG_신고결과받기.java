#include <string>
#include <vector>
#include <iostream>

const int MAX=1001;

using namespace std;

struct REPORT{
    string name;
    vector<string> declare;
    int n;
};
REPORT rpt[MAX];


vector<int> solution(vector<string> id_list, vector<string> report, int k) {
    vector<int> answer;
    
    for (int i=0; i<id_list.size(); i++) {
        string name=id_list[i];
        rpt[i].name=name;
    }
    
    for (int i=0; i<report.size(); i++) {
        string str=report[i];
        string a,b;
        for (int j=0; j<str.size(); j++) {
            if (str[j]==' ') {
                a=str.substr(0, j);
                b=str.substr(j+1, str.size()-j+2);
                break;
            }
        }
        
        for (int i=0; i<id_list.size(); i++) {
            if (a==rpt[i].name) {
                bool flag=true;
                for (int j=0; j<rpt[i].declare.size(); j++) {
                    if (b==rpt[i].declare[j]) {
                        flag=false; break;
                    }
                }
                if (flag==true) {
                    rpt[i].declare.push_back(b);
                    
                    for (int j=0; j<id_list.size(); j++) {
                        if (rpt[j].name==b) {
                            rpt[j].n++;
                            break;
                        }
                    }
                }
                break;
            }
        }
    }
    
    vector<string> arrest;
    for (int i=0; i<id_list.size(); i++) {
        if (rpt[i].n>=k) {
            arrest.push_back(rpt[i].name);
        }
    }
    
    for (int i=0; i<id_list.size(); i++) {
        int cnt=0;
        for (int j=0; j<rpt[i].declare.size(); j++) {
            string a=rpt[i].declare[j];
            
            for (int k=0; k<arrest.size(); k++) {
                if (a==arrest[k]) {
                    cnt++;
                    break;
                }
            }
        }
        answer.push_back(cnt);
    }
    
    return answer;
}