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
    public class Email_categoryBRL
    {
        #region Init
        private static string EX_NOT_EXIST="Không Tồn Tại Email_category Này";
		private static string EX_ID_INVALID="_id không hợp lệ";
        #endregion
        #region Public Methods
        /// <summary>
        /// Get Email_category Theo ID
        /// </summary>
        /// <param name="PK_iEmail_categoryID">Int32:Email_category ID</param>
        /// <returns>Email_categoryEntity</returns>        
        public static Email_categoryEntity GetOne(short PK_iEmail_categoryID)
        {
            
			if(PK_iEmail_categoryID<=0)
				throw new Exception(EX_ID_INVALID);
            return Email_categoryDAL.GetOne(PK_iEmail_categoryID);
        }
        /// <summary>
        /// Lấy về List các Email_category
        /// </summary>
        /// <returns>List Email_categoryEntity:List Email_category Cần lấy</returns>
        public static List<Email_categoryEntity> GetAll()
        {
            return Email_categoryDAL.GetAll();
        }
        
        /// <summary>
        /// Kiểm tra và thêm mới Email_category
        /// </summary>
        /// <param name="entity">Entity</param>
        /// <returns>Int32: ID của Email_category Mới Thêm Vào</returns>
        public static Int32 Add(Email_categoryEntity entity)
        {
            checkLogic(entity);
            checkDuplicate(entity, false);
            checkFK(entity);
            return Email_categoryDAL.Add(entity);
        }
        /// <summary>
        /// Kiểm tra và chỉnh sửa Email_category
        /// </summary>
        /// <param name="entity">Email_categoryEntity</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Edit(Email_categoryEntity entity)
        {
            checkExist(entity._id);
            checkLogic(entity);
            checkDuplicate(entity, true);
            checkFK(entity);
            return Email_categoryDAL.Edit(entity);
        }
        /// <summary>
        /// Kiểm tra và xoá Email_category
        /// </summary>
        /// <param name="PK_iEmail_categoryID">Int32 : PK_iEmail_categoryID</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Remove(short PK_iEmail_categoryID)
        {
            checkExist(PK_iEmail_categoryID);
            return Email_categoryDAL.Remove(PK_iEmail_categoryID);
        }
        #endregion
        #region Private Methods
        private static void checkExist(short PK_iEmail_categoryID)
        {
            Email_categoryEntity oEmail_category=Email_categoryDAL.GetOne(PK_iEmail_categoryID);
            if(oEmail_category==null)
                throw new Exception(EX_NOT_EXIST);
        }
        /// <summary>
        /// Kiểm tra logic Entity
        /// </summary>
        /// <param name="entity">Email_categoryEntity: entity</param>
        private static void checkLogic(Email_categoryEntity entity)
        {
            //if (String.IsNullOrEmpty(entity.sLastname))
            //    throw new Exception(EX_SLASTNAME_EMPTY);
        }
        /// <summary>
        /// Kiểm tra trùng lặp bản ghi
        /// </summary>
        /// <param name="entity">Email_categoryEntity: Email_categoryEntity</param>
        private static void checkDuplicate(Email_categoryEntity entity,bool checkPK)
        {
            /* 
            Example
            List<Email_categoryEntity> list = Email_categoryDAL.GetAll();
            if (list.Exists(
                delegate(Email_categoryEntity oldEntity)
                {
                    bool result =oldEntity.FIELD.Equals(entity.FIELD, StringComparison.OrdinalIgnoreCase);
                    if(checkPK)
                        result=result && oldEntity.PK_iEmail_categoryID != entity.PK_iEmail_categoryID;
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
        /// <param name="entity">Email_categoryEntity:entity</param>
        private static void checkFK(Email_categoryEntity entity)
        {            
        }
        #endregion
    }
}
