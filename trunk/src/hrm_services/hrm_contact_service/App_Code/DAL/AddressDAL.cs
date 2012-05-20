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
    public class AddressDAL : SqlProvider<AddressEntity>
    {
        static AddressDAL()
        {
            InitReader();
        }
        protected static void InitReader()
        {
            getFromReader=delegate(SqlDataReader dr)
            {
                AddressEntity entity = new AddressEntity();
                entity._id = Int32.Parse("0" + dr["_id"].ToString());
                entity.sStreet = dr["sStreet"].ToString();
                entity.sCity = dr["sCity"].ToString();
                entity.sState = dr["sState"].ToString();
                entity.sZipcode = dr["sZipcode"].ToString();
                entity.FK_iAddress_categoryID = short.Parse("0" + dr["FK_iAddress_categoryID"].ToString());
                entity.FK_iContactID = int.Parse("0" + dr["FK_iContactID"].ToString());
                return entity;
            };
        }
        public static AddressEntity GetOne(Int32 PK_iAddressID)
        {
            string cmdName = "spAddress_GetOne";
            SqlParameter p = new SqlParameter("@_id", PK_iAddressID);
            AddressEntity entity = GetOne(cmdName, p);
            return entity;
        }
        public static List<AddressEntity> GetAll()
        {
            string cmdName = "spAddress_GetAll";
            return GetList(cmdName);
        }
        
        public static int Add(AddressEntity entity)
        {
            string cmdName = "spAddress_Insert";
            return Run(cmdName,true,initParams(entity));

        }
        public static bool Edit(AddressEntity entity)
        {
            string cmdName = "spAddress_Update";
            return Run(cmdName,false,initParams(entity))>0;
        }
        public static bool Remove(Int32 PK_iAddressID)
        {
            string cmdName = "spAddress_Delete";
            SqlParameter p = new SqlParameter("@_id", PK_iAddressID);
            return Run(cmdName,false,p)>0;
        }
        #region private
        private static SqlParameter[] initParams(AddressEntity entity)
        {
            SqlParameter[] p = new SqlParameter[7];
			p[0] = new SqlParameter("@_id", entity._id);
			p[1] = new SqlParameter("@sStreet", entity.sStreet);
			p[2] = new SqlParameter("@sCity", entity.sCity);
			p[3] = new SqlParameter("@sState", entity.sState);
            p[4] = new SqlParameter("@sZipcode", entity.sZipcode);
            p[5] = new SqlParameter("@FK_iAddress_categoryID", entity.FK_iAddress_categoryID);
            p[6] = new SqlParameter("@FK_iContactID", entity.FK_iContactID);
            return p;
        }
        #endregion
       
    }
}