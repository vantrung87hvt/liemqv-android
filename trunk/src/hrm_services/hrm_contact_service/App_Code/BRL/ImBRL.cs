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
    public class ImBRL
    {
        #region Init
        private static string EX_NOT_EXIST="Không Tồn Tại Im Này";
		private static string EX_ID_INVALID="_id không hợp lệ";
        #endregion
        #region Public Methods
        /// <summary>
        /// Get Im Theo ID
        /// </summary>
        /// <param name="PK_iImID">Int32:Im ID</param>
        /// <returns>ImEntity</returns>        
        public static ImEntity GetOne(Int32 PK_iImID)
        {
            
			if(PK_iImID<=0)
				throw new Exception(EX_ID_INVALID);
            return ImDAL.GetOne(PK_iImID);
        }
        /// <summary>
        /// Lấy về List các Im
        /// </summary>
        /// <returns>List ImEntity:List Im Cần lấy</returns>
        public static List<ImEntity> GetAll()
        {
            return ImDAL.GetAll();
        }
        
        /// <summary>
        /// Kiểm tra và thêm mới Im
        /// </summary>
        /// <param name="entity">Entity</param>
        /// <returns>Int32: ID của Im Mới Thêm Vào</returns>
        public static Int32 Add(ImEntity entity)
        {
            checkLogic(entity);
            checkDuplicate(entity, false);
            checkFK(entity);
            return ImDAL.Add(entity);
        }
        /// <summary>
        /// Kiểm tra và chỉnh sửa Im
        /// </summary>
        /// <param name="entity">ImEntity</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Edit(ImEntity entity)
        {
            checkExist(entity._id);
            checkLogic(entity);
            checkDuplicate(entity, true);
            checkFK(entity);
            return ImDAL.Edit(entity);
        }
        /// <summary>
        /// Kiểm tra và xoá Im
        /// </summary>
        /// <param name="PK_iImID">Int32 : PK_iImID</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Remove(Int32 PK_iImID)
        {
            checkExist(PK_iImID);
            return ImDAL.Remove(PK_iImID);
        }
        #endregion
        #region Private Methods
        private static void checkExist(Int32 PK_iImID)
        {
            ImEntity oIm=ImDAL.GetOne(PK_iImID);
            if(oIm==null)
                throw new Exception(EX_NOT_EXIST);
        }
        /// <summary>
        /// Kiểm tra logic Entity
        /// </summary>
        /// <param name="entity">ImEntity: entity</param>
        private static void checkLogic(ImEntity entity)
        {
            //if (String.IsNullOrEmpty(entity.sLastname))
            //    throw new Exception(EX_SLASTNAME_EMPTY);
        }
        /// <summary>
        /// Kiểm tra trùng lặp bản ghi
        /// </summary>
        /// <param name="entity">ImEntity: ImEntity</param>
        private static void checkDuplicate(ImEntity entity,bool checkPK)
        {
            /* 
            Example
            List<ImEntity> list = ImDAL.GetAll();
            if (list.Exists(
                delegate(ImEntity oldEntity)
                {
                    bool result =oldEntity.FIELD.Equals(entity.FIELD, StringComparison.OrdinalIgnoreCase);
                    if(checkPK)
                        result=result && oldEntity.PK_iImID != entity.PK_iImID;
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
        /// <param name="entity">ImEntity:entity</param>
        private static void checkFK(ImEntity entity)
        {            
        }
        #endregion
    }
}
