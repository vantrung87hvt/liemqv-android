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
    public class Address_categoryBRL
    {
        #region Init
        private static string EX_NOT_EXIST="Không Tồn Tại Address_category Này";
		private static string EX_ID_INVALID="_id không hợp lệ";
        #endregion
        #region Public Methods
        /// <summary>
        /// Get Address_category Theo ID
        /// </summary>
        /// <param name="PK_iAddress_categoryID">Int32:Address_category ID</param>
        /// <returns>Address_categoryEntity</returns>        
        public static Address_categoryEntity GetOne(short PK_iAddress_categoryID)
        {
            
			if(PK_iAddress_categoryID<=0)
				throw new Exception(EX_ID_INVALID);
            return Address_categoryDAL.GetOne(PK_iAddress_categoryID);
        }
        /// <summary>
        /// Lấy về List các Address_category
        /// </summary>
        /// <returns>List Address_categoryEntity:List Address_category Cần lấy</returns>
        public static List<Address_categoryEntity> GetAll()
        {
            return Address_categoryDAL.GetAll();
        }
        
        /// <summary>
        /// Kiểm tra và thêm mới Address_category
        /// </summary>
        /// <param name="entity">Entity</param>
        /// <returns>Int32: ID của Address_category Mới Thêm Vào</returns>
        public static Int32 Add(Address_categoryEntity entity)
        {
            checkLogic(entity);
            checkDuplicate(entity, false);
            checkFK(entity);
            return Address_categoryDAL.Add(entity);
        }
        /// <summary>
        /// Kiểm tra và chỉnh sửa Address_category
        /// </summary>
        /// <param name="entity">Address_categoryEntity</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Edit(Address_categoryEntity entity)
        {
            checkExist(entity._id);
            checkLogic(entity);
            checkDuplicate(entity, true);
            checkFK(entity);
            return Address_categoryDAL.Edit(entity);
        }
        /// <summary>
        /// Kiểm tra và xoá Address_category
        /// </summary>
        /// <param name="PK_iAddress_categoryID">Int32 : PK_iAddress_categoryID</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Remove(short PK_iAddress_categoryID)
        {
            checkExist(PK_iAddress_categoryID);
            return Address_categoryDAL.Remove(PK_iAddress_categoryID);
        }
        #endregion
        #region Private Methods
        private static void checkExist(short PK_iAddress_categoryID)
        {
            Address_categoryEntity oAddress_category=Address_categoryDAL.GetOne(PK_iAddress_categoryID);
            if(oAddress_category==null)
                throw new Exception(EX_NOT_EXIST);
        }
        /// <summary>
        /// Kiểm tra logic Entity
        /// </summary>
        /// <param name="entity">Address_categoryEntity: entity</param>
        private static void checkLogic(Address_categoryEntity entity)
        {
            //if (String.IsNullOrEmpty(entity.sLastname))
            //    throw new Exception(EX_SLASTNAME_EMPTY);
        }
        /// <summary>
        /// Kiểm tra trùng lặp bản ghi
        /// </summary>
        /// <param name="entity">Address_categoryEntity: Address_categoryEntity</param>
        private static void checkDuplicate(Address_categoryEntity entity,bool checkPK)
        {
            /* 
            Example
            List<Address_categoryEntity> list = Address_categoryDAL.GetAll();
            if (list.Exists(
                delegate(Address_categoryEntity oldEntity)
                {
                    bool result =oldEntity.FIELD.Equals(entity.FIELD, StringComparison.OrdinalIgnoreCase);
                    if(checkPK)
                        result=result && oldEntity.PK_iAddress_categoryID != entity.PK_iAddress_categoryID;
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
        /// <param name="entity">Address_categoryEntity:entity</param>
        private static void checkFK(Address_categoryEntity entity)
        {            
        }
        #endregion
    }
}
