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
    public class WebsiteBRL
    {
        #region Init
        private static string EX_NOT_EXIST="Không Tồn Tại Website Này";
		private static string EX_ID_INVALID="_id không hợp lệ";
        #endregion
        #region Public Methods
        /// <summary>
        /// Get Website Theo ID
        /// </summary>
        /// <param name="PK_iWebsiteID">Int32:Website ID</param>
        /// <returns>WebsiteEntity</returns>        
        public static WebsiteEntity GetOne(Int32 PK_iWebsiteID)
        {
            
			if(PK_iWebsiteID<=0)
				throw new Exception(EX_ID_INVALID);
            return WebsiteDAL.GetOne(PK_iWebsiteID);
        }
        /// <summary>
        /// Lấy về List các Website
        /// </summary>
        /// <returns>List WebsiteEntity:List Website Cần lấy</returns>
        public static List<WebsiteEntity> GetAll()
        {
            return WebsiteDAL.GetAll();
        }
        
        /// <summary>
        /// Kiểm tra và thêm mới Website
        /// </summary>
        /// <param name="entity">Entity</param>
        /// <returns>Int32: ID của Website Mới Thêm Vào</returns>
        public static Int32 Add(WebsiteEntity entity)
        {
            checkLogic(entity);
            checkDuplicate(entity, false);
            checkFK(entity);
            return WebsiteDAL.Add(entity);
        }
        /// <summary>
        /// Kiểm tra và chỉnh sửa Website
        /// </summary>
        /// <param name="entity">WebsiteEntity</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Edit(WebsiteEntity entity)
        {
            checkExist(entity._id);
            checkLogic(entity);
            checkDuplicate(entity, true);
            checkFK(entity);
            return WebsiteDAL.Edit(entity);
        }
        /// <summary>
        /// Kiểm tra và xoá Website
        /// </summary>
        /// <param name="PK_iWebsiteID">Int32 : PK_iWebsiteID</param>
        /// <returns>bool:kết quả thực hiện</returns>
        public static bool Remove(Int32 PK_iWebsiteID)
        {
            checkExist(PK_iWebsiteID);
            return WebsiteDAL.Remove(PK_iWebsiteID);
        }
        #endregion
        #region Private Methods
        private static void checkExist(Int32 PK_iWebsiteID)
        {
            WebsiteEntity oWebsite=WebsiteDAL.GetOne(PK_iWebsiteID);
            if(oWebsite==null)
                throw new Exception(EX_NOT_EXIST);
        }
        /// <summary>
        /// Kiểm tra logic Entity
        /// </summary>
        /// <param name="entity">WebsiteEntity: entity</param>
        private static void checkLogic(WebsiteEntity entity)
        {
            //if (String.IsNullOrEmpty(entity.sLastname))
            //    throw new Exception(EX_SLASTNAME_EMPTY);
        }
        /// <summary>
        /// Kiểm tra trùng lặp bản ghi
        /// </summary>
        /// <param name="entity">WebsiteEntity: WebsiteEntity</param>
        private static void checkDuplicate(WebsiteEntity entity,bool checkPK)
        {
            /* 
            Example
            List<WebsiteEntity> list = WebsiteDAL.GetAll();
            if (list.Exists(
                delegate(WebsiteEntity oldEntity)
                {
                    bool result =oldEntity.FIELD.Equals(entity.FIELD, StringComparison.OrdinalIgnoreCase);
                    if(checkPK)
                        result=result && oldEntity.PK_iWebsiteID != entity.PK_iWebsiteID;
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
        /// <param name="entity">WebsiteEntity:entity</param>
        private static void checkFK(WebsiteEntity entity)
        {            
        }
        #endregion
    }
}
