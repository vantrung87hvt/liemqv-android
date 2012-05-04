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
    public class OrganizationDAL : SqlProvider<OrganizationEntity>
    {
        static OrganizationDAL()
        {
            InitReader();
        }
        protected static void InitReader()
        {
            getFromReader=delegate(SqlDataReader dr)
            {
                OrganizationEntity entity = new OrganizationEntity();
				entity._id = Int32.Parse("0"+dr["_id"].ToString());
				entity.sCompany = dr["sCompany"].ToString();
                entity.sTitle = dr["sTitle"].ToString();
                entity.FK_iContactID = Int32.Parse("0" + dr["FK_iContactID"].ToString());
                return entity;
            };
        }
        public static OrganizationEntity GetOne(Int32 PK_iOrganizationID)
        {
            string cmdName = "spOrganization_GetOne";
            SqlParameter p = new SqlParameter("@_id", PK_iOrganizationID);
            OrganizationEntity entity = GetOne(cmdName, p);
            return entity;
        }
        public static List<OrganizationEntity> GetAll()
        {
            string cmdName = "spOrganization_GetAll";
            return GetList(cmdName);
        }
        
        public static int Add(OrganizationEntity entity)
        {
            string cmdName = "spOrganization_Insert";
            return Run(cmdName,true,initParams(entity));

        }
        public static bool Edit(OrganizationEntity entity)
        {
            string cmdName = "spOrganization_Update";
            return Run(cmdName,false,initParams(entity))>0;
        }
        public static bool Remove(Int32 PK_iOrganizationID)
        {
            string cmdName = "spOrganization_Delete";
            SqlParameter p = new SqlParameter("@_id", PK_iOrganizationID);
            return Run(cmdName,false,p)>0;
        }
        #region private
        private static SqlParameter[] initParams(OrganizationEntity entity)
        {
            SqlParameter[] p = new SqlParameter[7];
			p[0] = new SqlParameter("@_id", entity._id);
			p[1] = new SqlParameter("@sCompany", entity.sCompany);
			p[2] = new SqlParameter("@sTitle", entity.sTitle);
			p[3] = new SqlParameter("@FK_iContactID", entity.FK_iContactID);
            return p;
        }
        #endregion
       
    }
}