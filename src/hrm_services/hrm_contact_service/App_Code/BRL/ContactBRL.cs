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
    public class ContactBRL
    {
        #region Init
        private static string EX_NOT_EXIST="Không Tồn Tại Contact Này";
		private static string EX_SLASTNAME_EMPTY="sLastname không được để trống";
		private static string EX_ID_INVALID="_id không hợp lệ";
        #endregion
        #region Public Methods
        /// <summary>
        /// Get Contact Theo ID
        /// </summary>
        /// <param name="PK_iContactID">Int32:Contact ID</param>
        /// <returns>ContactEntity</returns>        
        public static ContactEntity GetOne(Int32 PK_iContactID)
        {
            
			if(PK_iContactID<=0)
				throw new Exception(EX_ID_INVALID);
            return ContactDAL.GetOne(PK_iContactID);
        }
        /// <summary>
        /// Lấy về List các Contact
        /// </summary>
        /// <returns>List ContactEntity:List Contact Cần lấy</returns>
        public static List<ContactEntity> GetAll()
        {
            return ContactDAL.GetAll();
        }
        
        /// <summary>
        /// Kiểm tra và thêm mới Contact
        /// </summary>
        /// <param name="entity">Entity</param>
        /// <returns>Int32: ID của Contact Mới Thêm Vào</returns>
        public static Int32 Add(ContactEntity entity)
        {
            checkLogic(entity);
            checkDuplicate(entity, false);
            checkFK(entity);
            return ContactDAL.Add(entity);
        }
        /// <summary>
        /// Kiểm tra và chỉnh sửa Contact
        /// </summary>
        /// <param name="entity">ContactEntity</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Edit(ContactEntity entity)
        {
            checkExist(entity._id);
            checkLogic(entity);
            checkDuplicate(entity, true);
            checkFK(entity);
            return ContactDAL.Edit(entity);
        }
        /// <summary>
        /// Kiểm tra và xoá Contact
        /// </summary>
        /// <param name="PK_iContactID">Int32 : PK_iContactID</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Remove(Int32 PK_iContactID)
        {
            checkExist(PK_iContactID);
            return ContactDAL.Remove(PK_iContactID);
        }
        #endregion
        #region Private Methods
        private static void checkExist(Int32 PK_iContactID)
        {
            ContactEntity oContact=ContactDAL.GetOne(PK_iContactID);
            if(oContact==null)
                throw new Exception(EX_NOT_EXIST);
        }
        /// <summary>
        /// Kiểm tra logic Entity
        /// </summary>
        /// <param name="entity">ContactEntity: entity</param>
        private static void checkLogic(ContactEntity entity)
        {   
			if (String.IsNullOrEmpty(entity.sLastname))
				throw new Exception(EX_SLASTNAME_EMPTY);
        }
        /// <summary>
        /// Kiểm tra trùng lặp bản ghi
        /// </summary>
        /// <param name="entity">ContactEntity: ContactEntity</param>
        private static void checkDuplicate(ContactEntity entity,bool checkPK)
        {
            /* 
            Example
            List<ContactEntity> list = ContactDAL.GetAll();
            if (list.Exists(
                delegate(ContactEntity oldEntity)
                {
                    bool result =oldEntity.FIELD.Equals(entity.FIELD, StringComparison.OrdinalIgnoreCase);
                    if(checkPK)
                        result=result && oldEntity.PK_iContactID != entity.PK_iContactID;
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
        /// <param name="entity">ContactEntity:entity</param>
        private static void checkFK(ContactEntity entity)
        {            
        }
        #endregion
    }
}
