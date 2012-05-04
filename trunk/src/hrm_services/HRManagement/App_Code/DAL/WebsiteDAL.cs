using hrm_contact.DataAccess.Base;
using hrm_contact.Entity;
using System;
using System.Data;
using System.Data.SqlClient;
using System.Configuration;
using System.Collections.Generic;
using System.Collections;
using System.Web;

namespace hrm_contact.DataAccess
{    
    public class WebsiteDAL : SqlProvider<WebsiteEntity>
    {
        static WebsiteDAL()
        {
            InitReader();
        }
        protected static void InitReader()
        {
            getFromReader=delegate(SqlDataReader dr)
            {
                WebsiteEntity entity = new WebsiteEntity();
				entity._id = Int32.Parse("0"+dr["_id"].ToString());
				entity.sWebsite = dr["sWebsite"].ToString();
                entity.FK_iContactID = Int32.Parse("0" + dr["FK_iContactID"].ToString());
                return entity;
            };
        }
        public static WebsiteEntity GetOne(Int32 PK_iWebsiteID)
        {
            string cmdName = "spWebsite_GetOne";
            SqlParameter p = new SqlParameter("@_id", PK_iWebsiteID);
            WebsiteEntity entity = GetOne(cmdName, p);
            return entity;
        }
        public static List<WebsiteEntity> GetAll()
        {
            string cmdName = "spWebsite_GetAll";
            return GetList(cmdName);
        }
        
        public static int Add(WebsiteEntity entity)
        {
            string cmdName = "spWebsite_Insert";
            return Run(cmdName,true,initParams(entity));

        }
        public static bool Edit(WebsiteEntity entity)
        {
            string cmdName = "spWebsite_Update";
            return Run(cmdName,false,initParams(entity))>0;
        }
        public static bool Remove(Int32 PK_iWebsiteID)
        {
            string cmdName = "spWebsite_Delete";
            SqlParameter p = new SqlParameter("@_id", PK_iWebsiteID);
            return Run(cmdName,false,p)>0;
        }
        #region private
        private static SqlParameter[] initParams(WebsiteEntity entity)
        {
            SqlParameter[] p = new SqlParameter[3];
			p[0] = new SqlParameter("@_id", entity._id);
			p[1] = new SqlParameter("@sWebsite", entity.sWebsite);
			p[2] = new SqlParameter("@FK_iContactID", entity.FK_iContactID);
            return p;
        }
        #endregion
       
    }
}