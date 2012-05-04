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
    public class EmailBRL
    {
        #region Init
        private static string EX_NOT_EXIST="Không Tồn Tại Email Này";
		private static string EX_ID_INVALID="_id không hợp lệ";
        #endregion
        #region Public Methods
        /// <summary>
        /// Get Email Theo ID
        /// </summary>
        /// <param name="PK_iEmailID">Int32:Email ID</param>
        /// <returns>EmailEntity</returns>        
        public static EmailEntity GetOne(Int32 PK_iEmailID)
        {
            
			if(PK_iEmailID<=0)
				throw new Exception(EX_ID_INVALID);
            return EmailDAL.GetOne(PK_iEmailID);
        }
        /// <summary>
        /// Lấy về List các Email
        /// </summary>
        /// <returns>List EmailEntity:List Email Cần lấy</returns>
        public static List<EmailEntity> GetAll()
        {
            return EmailDAL.GetAll();
        }
        
        /// <summary>
        /// Kiểm tra và thêm mới Email
        /// </summary>
        /// <param name="entity">Entity</param>
        /// <returns>Int32: ID của Email Mới Thêm Vào</returns>
        public static Int32 Add(EmailEntity entity)
        {
            checkLogic(entity);
            checkDuplicate(entity, false);
            checkFK(entity);
            return EmailDAL.Add(entity);
        }
        /// <summary>
        /// Kiểm tra và chỉnh sửa Email
        /// </summary>
        /// <param name="entity">EmailEntity</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Edit(EmailEntity entity)
        {
            checkExist(entity._id);
            checkLogic(entity);
            checkDuplicate(entity, true);
            checkFK(entity);
            return EmailDAL.Edit(entity);
        }
        /// <summary>
        /// Kiểm tra và xoá Email
        /// </summary>
        /// <param name="PK_iEmailID">Int32 : PK_iEmailID</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Remove(Int32 PK_iEmailID)
        {
            checkExist(PK_iEmailID);
            return EmailDAL.Remove(PK_iEmailID);
        }
        #endregion
        #region Private Methods
        private static void checkExist(Int32 PK_iEmailID)
        {
            EmailEntity oEmail=EmailDAL.GetOne(PK_iEmailID);
            if(oEmail==null)
                throw new Exception(EX_NOT_EXIST);
        }
        /// <summary>
        /// Kiểm tra logic Entity
        /// </summary>
        /// <param name="entity">EmailEntity: entity</param>
        private static void checkLogic(EmailEntity entity)
        {
            //if (String.IsNullOrEmpty(entity.sLastname))
            //    throw new Exception(EX_SLASTNAME_EMPTY);
        }
        /// <summary>
        /// Kiểm tra trùng lặp bản ghi
        /// </summary>
        /// <param name="entity">EmailEntity: EmailEntity</param>
        private static void checkDuplicate(EmailEntity entity,bool checkPK)
        {
            /* 
            Example
            List<EmailEntity> list = EmailDAL.GetAll();
            if (list.Exists(
                delegate(EmailEntity oldEntity)
                {
                    bool result =oldEntity.FIELD.Equals(entity.FIELD, StringComparison.OrdinalIgnoreCase);
                    if(checkPK)
                        result=result && oldEntity.PK_iEmailID != entity.PK_iEmailID;
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
        /// <param name="entity">EmailEntity:entity</param>
        private static void checkFK(EmailEntity entity)
        {            
        }
        #endregion
    }
}
