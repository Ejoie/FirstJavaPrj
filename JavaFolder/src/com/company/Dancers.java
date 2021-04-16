package com.company;
import java.util.*;

public class Dancers {

        class dance
        {
            private String name;
            private Double scope;

            public dance(String Name, Double Scope)
            {
                this.name = Name;
                this.scope = Scope;
            }

            public Double getScope() {
                return scope;
            }

            public String getName() {
                return name;
            }
        }

        List<dance> ADance = new ArrayList<dance>();
        List<dance> FDance = new ArrayList<dance>();
        List<dance> SDance = new ArrayList<dance>();
        List<dance> TDance = new ArrayList<dance>();
        Comparator<dance> compareByScope = (dance d1, dance d2) -> d2.getScope().compareTo(d1.getScope());

        private boolean checkDance(List<dance> da, String s){
            return da.stream().anyMatch((d) -> d.getName().equals(s));
        }

        private dance findDance(String name, List<dance> da){
            return da.stream()
                    .filter(di -> name.equals(di.getName()))
                    .findAny()
                    .orElse(null);
        }

        private String addPart(List<dance> da, String s){
            String answer;

            String[] sp = s.split(";");

            if (checkDance(da,sp[2])) answer ="dancer yet is!";
            else {
                double sum = 0;
                for (int i = 3; i < 9; i++)
                    sum +=Double.parseDouble(sp[i]);
                sum = sum / 6;
                da.add(new dance(sp[2], sum));
                ReAllDancers(sp[2]);
                da.sort(compareByScope);
                answer = sp[2]+ " "+ sp[1]+ " " + Double.toString(sum);
            }
            return answer;
        }

       /* Comparator<dance> compareByScope = new Comparator<dance>() {
          @Override
          public int compare(dance d1, dance d2) {
              return d2.getScope().compareTo(d1.getScope());
          }
        }; */
        private String addDance(String req)
        {
            String answer;
            switch (req.charAt(2))
            {
                case '1':
                   answer = addPart(FDance,req);
                break;

                case '2':
                    answer = addPart(SDance,req);
                break;

                case '3':
                    answer = addPart(TDance,req);
                break;

                default:
                    answer = "empty!";
                    break;
            }
            return answer;
        }

        private String ansDance(String req)
        {
            String answer;
            String[] r = req.split(";");
            switch (r[2])
            {
                case "1":
                    if (checkDance(FDance,r[1])) {
                        answer = String.valueOf(FDance.indexOf(findDance(r[1],FDance)) + 1) + " mesto";
                    }
                    else answer = "Invalid";
                    break;
                case "2":
                    if (checkDance(SDance,r[1])) {
                        answer = String.valueOf(SDance.indexOf(findDance(r[1],SDance)) + 1) + " mesto";
                    }
                    else answer = "Invalid";
                    break;
                case "3":
                    if (checkDance(TDance,r[1])) {
                        answer = String.valueOf(TDance.indexOf(findDance(r[1],TDance)) + 1) + " mesto";
                    }
                    else answer = "Invalid";
                    break;
                default:
                    answer = "No dance!";
                    break;
            }
            return answer;
        }

        private void ReAllDancers(String name)
        {
            if (checkDance(ADance,name)) ADance.remove(findDance(name,ADance));
            double sum = 0;
            int del = 0;

            if (checkDance(FDance,name)) {
                sum += (findDance(name, FDance).scope);
                del++;
            }

            if (checkDance(SDance,name)) {
                sum += (findDance(name, SDance).scope);
                del++;
            }

            if (checkDance(TDance,name)) {
            sum += (findDance(name, TDance).scope);
            del++;
            }

            ADance.add(new dance(name, sum/del));
            ADance.sort(compareByScope);
        }

        private String topDance(String req)
        {
            String answer;
            String[] r = req.split(";");
            if (checkDance(ADance,r[1])) answer = String.valueOf(ADance.indexOf(findDance(r[1],ADance)) + 1) + " mesto";
            else answer = "no pchel!";
            return answer;
        }


        public String request(String req)
        {
            String answer;

            switch (req.charAt(0))
            {
                case '1':
                    answer = addDance(req);
                    break;

                case '2':
                    answer = ansDance(req);
                    break;

                case '3':
                    answer = topDance(req);
                    break;

                default:
                    answer = "mistake?";
                    break;
            }
            return answer;
        }


}
