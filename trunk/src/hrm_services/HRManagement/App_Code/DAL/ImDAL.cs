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
    public class ImDAL : SqlProvider<ImEntity>
    {
        static ImDAL()
        {
            InitReader();
        }
        protected static void InitReader()
        {
            getFromReader=delegate(SqlDataReader dr)
            {
                ImEntity entity = new ImEntity();
				entity._id = Int32.Parse("0"+dr["_id"].ToString());
				entity.sIm = dr["sIm"].ToString();
                entity.FK_iIm_categoryID = short.Parse("0" + dr["FK_iIm_categoryID"].ToString());
                entity.FK_iContactID = Int32.Parse("0" + dr["FK_iContactID"].ToString());
                return entity;
            };
        }
        public static ImEntity GetOne(Int32 PK_iImID)
        {
            string cmdName = "spIm_GetOne";
            SqlParameter p = new SqlParameter("@_id", PK_iImID);
            ImEntity entity = GetOne(cmdName, p);
            return entity;
        }
        public static List<ImEntity> GetAll()
        {
            string cmdName = "spIm_GetAll";
            return GetList(cmdName);
        }
        
        public static int Add(ImEntity entity)
        {
            string cmdName = "spIm_Insert";
            return Run(cmdName,true,initParams(entity));

        }
        public static bool Edit(ImEntity entity)
        {
            string cmdName = "spIm_Update";
            return Run(cmdName,false,initParams(entity))>0;
        }
        public static bool Remove(Int32 PK_iImID)
        {
            string cmdName = "spIm_Delete";
            SqlParameter p = new SqlParameter("@_id", PK_iImID);
            return Run(cmdName,false,p)>0;
        }
        #region private
        private static SqlParameter[] initParams(ImEntity entity)
        {
            SqlParameter[] p = new SqlParameter[4];
			p[0] = new SqlParameter("@_id", entity._id);
			p[1] = new SqlParameter("@sIm", entity.sIm);
			p[2] = new SqlParameter("@FK_iIm_category", entity.FK_iIm_categoryID);
			p[3] = new SqlParameter("@FK_iContactID", entity.FK_iContactID);
            return p;
        }
        #endregion
       
    }
}