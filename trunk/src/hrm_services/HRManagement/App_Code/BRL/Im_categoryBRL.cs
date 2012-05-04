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
    public class Im_categoryBRL
    {
        #region Init
        private static string EX_NOT_EXIST="Không Tồn Tại Im_category Này";
		private static string EX_ID_INVALID="_id không hợp lệ";
        #endregion
        #region Public Methods
        /// <summary>
        /// Get Im_category Theo ID
        /// </summary>
        /// <param name="PK_iIm_categoryID">Int32:Im_category ID</param>
        /// <returns>Im_categoryEntity</returns>        
        public static Im_categoryEntity GetOne(short PK_iIm_categoryID)
        {
            
			if(PK_iIm_categoryID<=0)
				throw new Exception(EX_ID_INVALID);
            return Im_categoryDAL.GetOne(PK_iIm_categoryID);
        }
        /// <summary>
        /// Lấy về List các Im_category
        /// </summary>
        /// <returns>List Im_categoryEntity:List Im_category Cần lấy</returns>
        public static List<Im_categoryEntity> GetAll()
        {
            return Im_categoryDAL.GetAll();
        }
        
        /// <summary>
        /// Kiểm tra và thêm mới Im_category
        /// </summary>
        /// <param name="entity">Entity</param>
        /// <returns>Int32: ID của Im_category Mới Thêm Vào</returns>
        public static Int32 Add(Im_categoryEntity entity)
        {
            checkLogic(entity);
            checkDuplicate(entity, false);
            checkFK(entity);
            return Im_categoryDAL.Add(entity);
        }
        /// <summary>
        /// Kiểm tra và chỉnh sửa Im_category
        /// </summary>
        /// <param name="entity">Im_categoryEntity</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Edit(Im_categoryEntity entity)
        {
            checkExist(entity._id);
            checkLogic(entity);
            checkDuplicate(entity, true);
            checkFK(entity);
            return Im_categoryDAL.Edit(entity);
        }
        /// <summary>
        /// Kiểm tra và xoá Im_category
        /// </summary>
        /// <param name="PK_iIm_categoryID">Int32 : PK_iIm_categoryID</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Remove(short PK_iIm_categoryID)
        {
            checkExist(PK_iIm_categoryID);
            return Im_categoryDAL.Remove(PK_iIm_categoryID);
        }
        #endregion
        #region Private Methods
        private static void checkExist(short PK_iIm_categoryID)
        {
            Im_categoryEntity oIm_category=Im_categoryDAL.GetOne(PK_iIm_categoryID);
            if(oIm_category==null)
                throw new Exception(EX_NOT_EXIST);
        }
        /// <summary>
        /// Kiểm tra logic Entity
        /// </summary>
        /// <param name="entity">Im_categoryEntity: entity</param>
        private static void checkLogic(Im_categoryEntity entity)
        {
            //if (String.IsNullOrEmpty(entity.sLastname))
            //    throw new Exception(EX_SLASTNAME_EMPTY);
        }
        /// <summary>
        /// Kiểm tra trùng lặp bản ghi
        /// </summary>
        /// <param name="entity">Im_categoryEntity: Im_categoryEntity</param>
        private static void checkDuplicate(Im_categoryEntity entity,bool checkPK)
        {
            /* 
            Example
            List<Im_categoryEntity> list = Im_categoryDAL.GetAll();
            if (list.Exists(
                delegate(Im_categoryEntity oldEntity)
                {
                    bool result =oldEntity.FIELD.Equals(entity.FIELD, StringComparison.OrdinalIgnoreCase);
                    if(checkPK)
                        result=result && oldEntity.PK_iIm_categoryID != entity.PK_iIm_categoryID;
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
        /// <param name="entity">Im_categoryEntity:entity</param>
        private static void checkFK(Im_categoryEntity entity)
        {            
        }
        #endregion
    }
}
