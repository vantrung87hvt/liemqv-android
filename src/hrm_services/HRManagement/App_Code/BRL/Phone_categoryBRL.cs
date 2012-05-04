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
    public class Phone_categoryBRL
    {
        #region Init
        private static string EX_NOT_EXIST="Không Tồn Tại Phone_category Này";
		private static string EX_ID_INVALID="_id không hợp lệ";
        #endregion
        #region Public Methods
        /// <summary>
        /// Get Phone_category Theo ID
        /// </summary>
        /// <param name="PK_iPhone_categoryID">Int32:Phone_category ID</param>
        /// <returns>Phone_categoryEntity</returns>        
        public static Phone_categoryEntity GetOne(short PK_iPhone_categoryID)
        {
            
			if(PK_iPhone_categoryID<=0)
				throw new Exception(EX_ID_INVALID);
            return Phone_categoryDAL.GetOne(PK_iPhone_categoryID);
        }
        /// <summary>
        /// Lấy về List các Phone_category
        /// </summary>
        /// <returns>List Phone_categoryEntity:List Phone_category Cần lấy</returns>
        public static List<Phone_categoryEntity> GetAll()
        {
            return Phone_categoryDAL.GetAll();
        }
        
        /// <summary>
        /// Kiểm tra và thêm mới Phone_category
        /// </summary>
        /// <param name="entity">Entity</param>
        /// <returns>Int32: ID của Phone_category Mới Thêm Vào</returns>
        public static Int32 Add(Phone_categoryEntity entity)
        {
            checkLogic(entity);
            checkDuplicate(entity, false);
            checkFK(entity);
            return Phone_categoryDAL.Add(entity);
        }
        /// <summary>
        /// Kiểm tra và chỉnh sửa Phone_category
        /// </summary>
        /// <param name="entity">Phone_categoryEntity</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Edit(Phone_categoryEntity entity)
        {
            checkExist(entity._id);
            checkLogic(entity);
            checkDuplicate(entity, true);
            checkFK(entity);
            return Phone_categoryDAL.Edit(entity);
        }
        /// <summary>
        /// Kiểm tra và xoá Phone_category
        /// </summary>
        /// <param name="PK_iPhone_categoryID">Int32 : PK_iPhone_categoryID</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Remove(short PK_iPhone_categoryID)
        {
            checkExist(PK_iPhone_categoryID);
            return Phone_categoryDAL.Remove(PK_iPhone_categoryID);
        }
        #endregion
        #region Private Methods
        private static void checkExist(short PK_iPhone_categoryID)
        {
            Phone_categoryEntity oPhone_category=Phone_categoryDAL.GetOne(PK_iPhone_categoryID);
            if(oPhone_category==null)
                throw new Exception(EX_NOT_EXIST);
        }
        /// <summary>
        /// Kiểm tra logic Entity
        /// </summary>
        /// <param name="entity">Phone_categoryEntity: entity</param>
        private static void checkLogic(Phone_categoryEntity entity)
        {
            //if (String.IsNullOrEmpty(entity.sLastname))
            //    throw new Exception(EX_SLASTNAME_EMPTY);
        }
        /// <summary>
        /// Kiểm tra trùng lặp bản ghi
        /// </summary>
        /// <param name="entity">Phone_categoryEntity: Phone_categoryEntity</param>
        private static void checkDuplicate(Phone_categoryEntity entity,bool checkPK)
        {
            /* 
            Example
            List<Phone_categoryEntity> list = Phone_categoryDAL.GetAll();
            if (list.Exists(
                delegate(Phone_categoryEntity oldEntity)
                {
                    bool result =oldEntity.FIELD.Equals(entity.FIELD, StringComparison.OrdinalIgnoreCase);
                    if(checkPK)
                        result=result && oldEntity.PK_iPhone_categoryID != entity.PK_iPhone_categoryID;
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
        /// <param name="entity">Phone_categoryEntity:entity</param>
        private static void checkFK(Phone_categoryEntity entity)
        {            
        }
        #endregion
    }
}
