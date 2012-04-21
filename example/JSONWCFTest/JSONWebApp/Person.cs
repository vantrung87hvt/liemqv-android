using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace JSONWebApp
{
    public class Person
    {
        private String sName;

        public String SName
        {
            get { return sName; }
            set { sName = value; }
        }
        private int iAge;

        public int IAge
        {
            get { return iAge; }
            set { iAge = value; }
        }

    }
}