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
    public class PhoneBRL
    {
        #region Init
        private static string EX_NOT_EXIST="Không Tồn Tại Phone Này";
		private static string EX_ID_INVALID="_id không hợp lệ";
        #endregion
        #region Public Methods
        /// <summary>
        /// Get Phone Theo ID
        /// </summary>
        /// <param name="PK_iPhoneID">Int32:Phone ID</param>
        /// <returns>PhoneEntity</returns>        
        public static PhoneEntity GetOne(Int32 PK_iPhoneID)
        {
            
			if(PK_iPhoneID<=0)
				throw new Exception(EX_ID_INVALID);
            return PhoneDAL.GetOne(PK_iPhoneID);
        }
        /// <summary>
        /// Lấy về List các Phone
        /// </summary>
        /// <returns>List PhoneEntity:List Phone Cần lấy</returns>
        public static List<PhoneEntity> GetAll()
        {
            return PhoneDAL.GetAll();
        }
        
        /// <summary>
        /// Kiểm tra và thêm mới Phone
        /// </summary>
        /// <param name="entity">Entity</param>
        /// <returns>Int32: ID của Phone Mới Thêm Vào</returns>
        public static Int32 Add(PhoneEntity entity)
        {
            checkLogic(entity);
            checkDuplicate(entity, false);
            checkFK(entity);
            return PhoneDAL.Add(entity);
        }
        /// <summary>
        /// Kiểm tra và chỉnh sửa Phone
        /// </summary>
        /// <param name="entity">PhoneEntity</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Edit(PhoneEntity entity)
        {
            checkExist(entity._id);
            checkLogic(entity);
            checkDuplicate(entity, true);
            checkFK(entity);
            return PhoneDAL.Edit(entity);
        }
        /// <summary>
        /// Kiểm tra và xoá Phone
        /// </summary>
        /// <param name="PK_iPhoneID">Int32 : PK_iPhoneID</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Remove(Int32 PK_iPhoneID)
        {
            checkExist(PK_iPhoneID);
            return PhoneDAL.Remove(PK_iPhoneID);
        }
        #endregion
        #region Private Methods
        private static void checkExist(Int32 PK_iPhoneID)
        {
            PhoneEntity oPhone=PhoneDAL.GetOne(PK_iPhoneID);
            if(oPhone==null)
                throw new Exception(EX_NOT_EXIST);
        }
        /// <summary>
        /// Kiểm tra logic Entity
        /// </summary>
        /// <param name="entity">PhoneEntity: entity</param>
        private static void checkLogic(PhoneEntity entity)
        {
            //if (String.IsNullOrEmpty(entity.sLastname))
            //    throw new Exception(EX_SLASTNAME_EMPTY);
        }
        /// <summary>
        /// Kiểm tra trùng lặp bản ghi
        /// </summary>
        /// <param name="entity">PhoneEntity: PhoneEntity</param>
        private static void checkDuplicate(PhoneEntity entity,bool checkPK)
        {
            /* 
            Example
            List<PhoneEntity> list = PhoneDAL.GetAll();
            if (list.Exists(
                delegate(PhoneEntity oldEntity)
                {
                    bool result =oldEntity.FIELD.Equals(entity.FIELD, StringComparison.OrdinalIgnoreCase);
                    if(checkPK)
                        result=result && oldEntity.PK_iPhoneID != entity.PK_iPhoneID;
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
        /// <param name="entity">PhoneEntity:entity</param>
        private static void checkFK(PhoneEntity entity)
        {            
        }
        #endregion
    }
}
