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
    public class AddressBRL
    {
        #region Init
        private static string EX_NOT_EXIST="Không Tồn Tại Address Này";
		private static string EX_ID_INVALID="_id không hợp lệ";
        #endregion
        #region Public Methods
        /// <summary>
        /// Get Address Theo ID
        /// </summary>
        /// <param name="PK_iAddressID">Int32:Address ID</param>
        /// <returns>AddressEntity</returns>        
        public static AddressEntity GetOne(Int32 PK_iAddressID)
        {
            
			if(PK_iAddressID<=0)
				throw new Exception(EX_ID_INVALID);
            return AddressDAL.GetOne(PK_iAddressID);
        }
        /// <summary>
        /// Lấy về List các Address
        /// </summary>
        /// <returns>List AddressEntity:List Address Cần lấy</returns>
        public static List<AddressEntity> GetAll()
        {
            return AddressDAL.GetAll();
        }
        
        /// <summary>
        /// Kiểm tra và thêm mới Address
        /// </summary>
        /// <param name="entity">Entity</param>
        /// <returns>Int32: ID của Address Mới Thêm Vào</returns>
        public static Int32 Add(AddressEntity entity)
        {
            checkLogic(entity);
            checkDuplicate(entity, false);
            checkFK(entity);
            return AddressDAL.Add(entity);
        }
        /// <summary>
        /// Kiểm tra và chỉnh sửa Address
        /// </summary>
        /// <param name="entity">AddressEntity</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Edit(AddressEntity entity)
        {
            checkExist(entity._id);
            checkLogic(entity);
            checkDuplicate(entity, true);
            checkFK(entity);
            return AddressDAL.Edit(entity);
        }
        /// <summary>
        /// Kiểm tra và xoá Address
        /// </summary>
        /// <param name="PK_iAddressID">Int32 : PK_iAddressID</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Remove(Int32 PK_iAddressID)
        {
            checkExist(PK_iAddressID);
            return AddressDAL.Remove(PK_iAddressID);
        }
        #endregion
        #region Private Methods
        private static void checkExist(Int32 PK_iAddressID)
        {
            AddressEntity oAddress=AddressDAL.GetOne(PK_iAddressID);
            if(oAddress==null)
                throw new Exception(EX_NOT_EXIST);
        }
        /// <summary>
        /// Kiểm tra logic Entity
        /// </summary>
        /// <param name="entity">AddressEntity: entity</param>
        private static void checkLogic(AddressEntity entity)
        {
            //if (String.IsNullOrEmpty(entity.sLastname))
            //    throw new Exception(EX_SLASTNAME_EMPTY);
        }
        /// <summary>
        /// Kiểm tra trùng lặp bản ghi
        /// </summary>
        /// <param name="entity">AddressEntity: AddressEntity</param>
        private static void checkDuplicate(AddressEntity entity,bool checkPK)
        {
            /* 
            Example
            List<AddressEntity> list = AddressDAL.GetAll();
            if (list.Exists(
                delegate(AddressEntity oldEntity)
                {
                    bool result =oldEntity.FIELD.Equals(entity.FIELD, StringComparison.OrdinalIgnoreCase);
                    if(checkPK)
                        result=result && oldEntity.PK_iAddressID != entity.PK_iAddressID;
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
        /// <param name="entity">AddressEntity:entity</param>
        private static void checkFK(AddressEntity entity)
        {            
        }
        #endregion
    }
}
