package com.abc.campusrecruitment;


    public class Apply {
        public String name;
        public String email;
        public String phone;
        public String userid;

        public Apply() {

        }

        Apply(String name, String email, String phone, String userid) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.userid = userid;
        }
        public String getname(){
            return name;
        }
        public String getphone(){
            return phone;
        }
        public String getemail(){
            return email;
        }

        public String getUserid() {
            return userid;
        }
    }