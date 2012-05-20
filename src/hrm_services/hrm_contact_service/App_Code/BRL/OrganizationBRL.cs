using hrm_contact.Entity;
using hrm_contact.DataAccess;
using System;
using System.Collections;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Collections.Generic;
using System.Text;
namespace hrm_contact.Business
{
    public class OrganizationBRL
    {
        #region Init
        private static string EX_NOT_EXIST="Không Tồn Tại Organization Này";
		private static string EX_ID_INVALID="_id không hợp lệ";
        #endregion
        #region Public Methods
        /// <summary>
        /// Get Organization Theo ID
        /// </summary>
        /// <param name="PK_iOrganizationID">Int32:Organization ID</param>
        /// <returns>OrganizationEntity</returns>        
        public static OrganizationEntity GetOne(Int32 PK_iOrganizationID)
        {
            
			if(PK_iOrganizationID<=0)
				throw new Exception(EX_ID_INVALID);
            return OrganizationDAL.GetOne(PK_iOrganizationID);
        }
        /// <summary>
        /// Lấy về List các Organization
        /// </summary>
        /// <returns>List OrganizationEntity:List Organization Cần lấy</returns>
        public static List<OrganizationEntity> GetAll()
        {
            return OrganizationDAL.GetAll();
        }
        
        /// <summary>
        /// Kiểm tra và thêm mới Organization
        /// </summary>
        /// <param name="entity">Entity</param>
        /// <returns>Int32: ID của Organization Mới Thêm Vào</returns>
        public static Int32 Add(OrganizationEntity entity)
        {
            checkLogic(entity);
            checkDuplicate(entity, false);
            checkFK(entity);
            return OrganizationDAL.Add(entity);
        }
        /// <summary>
        /// Kiểm tra và chỉnh sửa Organization
        /// </summary>
        /// <param name="entity">OrganizationEntity</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Edit(OrganizationEntity entity)
        {
            checkExist(entity._id);
            checkLogic(entity);
            checkDuplicate(entity, true);
            checkFK(entity);
            return OrganizationDAL.Edit(entity);
        }
        /// <summary>
        /// Kiểm tra và xoá Organization
        /// </summary>
        /// <param name="PK_iOrganizationID">Int32 : PK_iOrganizationID</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Remove(Int32 PK_iOrganizationID)
        {
            checkExist(PK_iOrganizationID);
            return OrganizationDAL.Remove(PK_iOrganizationID);
        }
        #endregion
        #region Private Methods
        private static void checkExist(Int32 PK_iOrganizationID)
        {
            OrganizationEntity oOrganization=OrganizationDAL.GetOne(PK_iOrganizationID);
            if(oOrganization==null)
                throw new Exception(EX_NOT_EXIST);
        }
        /// <summary>
        /// Kiểm tra logic Entity
        /// </summary>
        /// <param name="entity">OrganizationEntity: entity</param>
        private static void checkLogic(OrganizationEntity entity)
        {
            //if (String.IsNullOrEmpty(entity.sLastname))
            //    throw new Exception(EX_SLASTNAME_EMPTY);
        }
        /// <summary>
        /// Kiểm tra trùng lặp bản ghi
        /// </summary>
        /// <param name="entity">OrganizationEntity: OrganizationEntity</param>
        private static void checkDuplicate(OrganizationEntity entity,bool checkPK)
        {
            /* 
            Example
            List<OrganizationEntity> list = OrganizationDAL.GetAll();
            if (list.Exists(
                delegate(OrganizationEntity oldEntity)
                {
                    bool result =oldEntity.FIELD.Equals(entity.FIELD, StringComparison.OrdinalIgnoreCase);
                    if(checkPK)
                        result=result && oldEntity.PK_iOrganizationID != entity.PK_iOrganizationID;
                    return result;
                }
            ))
            {
                list.Clear();
                throw new Exception(EX_FIELD_EXISTED);
            }
            */
        }
        /// <summary>
        /// Kiểm tra tồn tại khóa ngoại
        /// </summary>
        /// <param name="entity">OrganizationEntity:entity</param>
        private static void checkFK(OrganizationEntity entity)
        {            
        }
        #endregion
    }
}
