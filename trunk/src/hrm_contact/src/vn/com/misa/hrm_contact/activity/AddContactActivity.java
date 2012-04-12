/**
 * Create by: liemqv
 * Create date: 12/04/2012 
 * Activity này để thêm một Contact mới vào database
 * */
package vn.com.misa.hrm_contact.activity;

import vn.com.misa.hrm_contact.R;
import vn.com.misa.hrm_contact.bean.Address;
import vn.com.misa.hrm_contact.bean.Contact;
import vn.com.misa.hrm_contact.bean.Email;
import vn.com.misa.hrm_contact.bean.Im;
import vn.com.misa.hrm_contact.bean.Organization;
import vn.com.misa.hrm_contact.bean.Phone;
import vn.com.misa.hrm_contact.sql.AddressBLL;
import vn.com.misa.hrm_contact.sql.ContactBLL;
import vn.com.misa.hrm_contact.sql.EmailBLL;
import vn.com.misa.hrm_contact.sql.ImBLL;
import vn.com.misa.hrm_contact.sql.OrganizationBLL;
import vn.com.misa.hrm_contact.sql.PhoneBLL;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddContactActivity extends Activity {
	private ContactBLL contactBll = null;
	private PhoneBLL phoneBll = null;
	private EmailBLL emailBll = null;
	private AddressBLL addressBll = null;
	private OrganizationBLL orgBll = null;
	private ImBLL imBll = null;
	
	private ImageButton btnAvatar = null;
	
	private EditText edtFirstName = null;
	private EditText edtLastName = null;
	
	private RadioGroup rdogrGender = null;
	private RadioButton rdoMale = null;
	
	private EditText edtPhoneHome = null;
	private EditText edtPhoneMobile = null;
	
	private EditText edtEmailHome = null;
	private EditText edtEmailWork = null;
	
	private EditText edtStreet = null;
	private EditText edtCity = null;
	private EditText edtState = null;
	private EditText edtZipcode = null;
	
	private EditText edtOrgCompany = null;
	private EditText edtOrgTitle = null;
	
	private EditText edtImSkype = null;
	private EditText edtImYahoo = null;
	
	private EditText edtNotes = null;
	
	private EditText edtNickname = null;
	
	private EditText edtWebsite = null;	
	
	private Button btnSave;
	private Button btnCancel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);
        try{
	        contactBll = new ContactBLL(this);
	        phoneBll = new PhoneBLL(this);
	        emailBll = new EmailBLL(this);
	        addressBll = new AddressBLL(this);
	        orgBll = new OrganizationBLL(this);
	        imBll = new ImBLL(this);
	        /* Danh sách các điều khiển có trong layout add_contact.xml */
	        /* Avatar */
	        btnAvatar = (ImageButton) findViewById(R.id.btn_avatar);
	        /* First + Last name */
	        edtFirstName = (EditText) findViewById(R.id.edt_first_name);
	        edtLastName = (EditText) findViewById(R.id.edt_last_name);
	        /* Gender */
	        rdogrGender = (RadioGroup) findViewById(R.id.rdogr_gender);
	        rdoMale = (RadioButton) findViewById(R.id.rdo_male);
	        /* Phone */
	        edtPhoneHome = (EditText) findViewById(R.id.edt_phone_home);
	        edtPhoneMobile = (EditText) findViewById(R.id.edt_phone_mobile);
	        /* Email */
	        edtEmailHome = (EditText) findViewById(R.id.edt_email_home);
	        edtEmailWork = (EditText) findViewById(R.id.edt_email_work);
	        /* Address */
	        edtStreet = (EditText) findViewById(R.id.edt_street);
	        edtCity = (EditText) findViewById(R.id.edt_city);
	        edtState = (EditText) findViewById(R.id.edt_state);
	        edtZipcode = (EditText) findViewById(R.id.edt_zipcode);
	        /* Organization */
	        edtOrgCompany = (EditText) findViewById(R.id.edt_org_company);
	        edtOrgTitle = (EditText) findViewById(R.id.edt_org_title);
	        /* IM */
	        edtImSkype = (EditText) findViewById(R.id.edt_im_skype);
	        edtImYahoo = (EditText) findViewById(R.id.edt_im_yahoo);
	        /* Notes */
	        edtNotes = (EditText) findViewById(R.id.edt_notes);
	        /* Nickname */
	        edtNickname = (EditText) findViewById(R.id.edt_nickname);
	        /* Website */
	        edtWebsite = (EditText) findViewById(R.id.edt_website);        
	        /* Action */
	        btnSave = (Button)findViewById(R.id.save_button);
	        btnCancel = (Button)findViewById(R.id.cancel_button);
	        
	        // Set Click Listener
	        btnSave.setOnClickListener(new OnClickListener() {
		  	@Override
		  	public void onClick(View v) {
		  		Contact contactRes = null;
		  		Intent intentResultStatus = null; //intentResultStatus để thông báo lại cho ContactIntent biết đã thêm thành công
		  		
		  		/*Khởi tạo một Contact mới để thêm vào database*/
		  		Contact contactAdd = new Contact();
		  		contactAdd.setsFirstname(edtFirstName.getText().toString());
		  		contactAdd.setsLastname(edtLastName.getText().toString());
		  		contactAdd.setiGender(rdoMale.isChecked() ? 0 : 1);
		  		contactAdd.setsNickname(edtNickname.getText().toString());
		  		contactAdd.setsNotes(edtNotes.getText().toString());
		  		
		  		
		  		/* Mở kết nối */
		  		contactBll.open();
		  		//Kiểm tra thông tin contact hiện tại đã tồn tại các thông tin (name, phone, email) chưa?
		  		boolean bCheckDup = contactBll.checkDupName(contactAdd.getsFirstname(), contactAdd.getsLastname());
		  		if(bCheckDup == true)
		  		{
			  		/* Gọi hàm thêm vào databse */
		  			intentResultStatus = new Intent();
			  		contactRes = contactBll.createContact(contactAdd);
			  		if(contactRes != null)
			  		{
				  		/* Insert danh sách các số điện thoại đi kèm với Contact hiện tại */
			  			phoneBll.open();
				  		if(!edtPhoneHome.getText().toString().equals(""))
				  		{
				  			Phone phoneHome = new Phone();
				  			phoneHome.setsNumber(edtPhoneHome.getText().toString());
				  			phoneHome.setiFK_Phone_categoryID(1); //XXX mặc định Category: Home có id là 1
				  			phoneHome.setiFK_ContactID(contactRes.getiId());
				  			/* Kiểm tra trùng số điện thoại */
				  			if(phoneBll.checkDupNumber(phoneHome.getsNumber()) == true)
				  			{
				  				phoneBll.createPhone(phoneHome);
				  			}
				  			else
				  			{
				  				Toast.makeText(getApplicationContext(), "Số điện thoại " + phoneHome.getsNumber() + " đã tồn tại!", Toast.LENGTH_LONG).show();
				  			}
				  		}
				  		if(!edtPhoneMobile.getText().toString().equals(""))
				  		{
				  			Phone phoneMobile = new Phone();
				  			phoneMobile.setsNumber(edtPhoneHome.getText().toString());
				  			phoneMobile.setiFK_Phone_categoryID(2); //XXX mặc định Category: Home có id là 2
				  			phoneMobile.setiFK_ContactID(contactRes.getiId());
				  			/* Kiểm tra trùng số điện thoại */
				  			if(phoneBll.checkDupNumber(phoneMobile.getsNumber()) == true)
				  			{
				  				phoneBll.createPhone(phoneMobile);
				  			}
				  			else
				  			{
				  				Toast.makeText(getApplicationContext(), "Số điện thoại " + phoneMobile.getsNumber() + " đã tồn tại!", Toast.LENGTH_LONG).show();
				  			}
				  		}
				  		phoneBll.close();
				  		/* Insert danh sách các email kèm theo */
			  			emailBll.open();
				  		if(!edtEmailHome.getText().toString().equals(""))
				  		{
				  			Email emailHome = new Email();
				  			emailHome.setsEmail(edtEmailHome.getText().toString());
				  			emailHome.setiFK_Email_categoryID(1); //XXX mặc định category email home là 1
				  			emailHome.setiFK_ContactID(contactRes.getiId());
				  			/* Kiểm tra trùng email */
				  			if(emailBll.checkDupEmail(emailHome.getsEmail()) == true)
				  			{
				  				emailBll.createEmail(emailHome);
				  			}
				  			else
				  			{
				  				Toast.makeText(getApplicationContext(), "Email " + emailHome.getsEmail() + " đã tồn tại!", Toast.LENGTH_LONG).show();
				  			}
				  		}
				  		if(!edtEmailWork.getText().toString().equals(""))
				  		{
				  			Email emailWork = new Email();
				  			emailWork.setsEmail(edtEmailHome.getText().toString());
				  			emailWork.setiFK_Email_categoryID(2); //XXX mặc định category email home là 1
				  			emailWork.setiFK_ContactID(contactRes.getiId());
				  			/* Kiểm tra trùng email */
				  			if(emailBll.checkDupEmail(emailWork.getsEmail()) == true)
				  			{
				  				emailBll.createEmail(emailWork);
				  			}
				  			else
				  			{
				  				Toast.makeText(getApplicationContext(), "Email " + emailWork.getsEmail() + " đã tồn tại!", Toast.LENGTH_LONG).show();
				  			}
				  		}
				  		emailBll.close();
				  		/* Insert địa chỉ */
			  			addressBll.open();
				  		if(!edtStreet.getText().toString().equals(""))
				  		{
				  			Address addressAdd = new Address();
				  			addressAdd.setsStreet(edtStreet.getText().toString());
				  			addressAdd.setsCity(edtCity.getText().toString());
				  			addressAdd.setsState(edtState.getText().toString());
				  			addressAdd.setsZipcode(edtZipcode.getText().toString());
				  			addressAdd.setiFK_Address_categoryID(1);
				  			addressAdd.setiFK_iContactID(contactRes.getiId());
				  			addressBll.createAddress(addressAdd);		
				  		}
				  		addressBll.close();
				  		/* Insert Organization */
			  			orgBll.open();
				  		if(!edtOrgCompany.getText().toString().equals(""))
				  		{
				  			Organization orgAdd = new Organization();
				  			orgAdd.setsCompany(edtOrgCompany.getText().toString());
				  			orgAdd.setsTitle(edtOrgTitle.getText().toString());
				  			orgAdd.setiFK_ContactID(contactRes.getiId());
				  			orgBll.createOrganization(orgAdd);
				  		}
				  		orgBll.close();
				  		/* Insert danh sách IM đính kèm */
			  			imBll.open();
				  		if(!edtImSkype.getText().toString().equals(""))
				  		{
				  			Im imSkype = new Im();
				  			imSkype.setsIm(edtImSkype.getText().toString());
				  			imSkype.setiFK_Im_categoryID(1);
				  			imSkype.setiFK_ContactID(contactRes.getiId());
				  			/* Kiểm tra trùng IM */
				  			if(imBll.checkDupIm(imSkype.getsIm()) == true)
				  			{
				  				imBll.createIm(imSkype);
				  			}
				  			else
				  			{
				  				Toast.makeText(getApplicationContext(), "IM " + imSkype.getsIm() + " đã tồn tại!", Toast.LENGTH_LONG).show();
				  			}
				  		}
				  		if(!edtImYahoo.getText().toString().equals(""))
				  		{
				  			Im imYahoo = new Im();
				  			imYahoo.setsIm(edtImYahoo.getText().toString());
				  			imYahoo.setiFK_Im_categoryID(2);
				  			imYahoo.setiFK_ContactID(contactRes.getiId());
				  			/* Kiểm tra trùng IM */
				  			if(imBll.checkDupIm(imYahoo.getsIm()) == true)
				  			{
				  				imBll.createIm(imYahoo);
				  			}
				  			else
				  			{
				  				Toast.makeText(getApplicationContext(), "IM " + imYahoo.getsIm() + " đã tồn tại!", Toast.LENGTH_LONG).show();
				  			}
				  		}
				  		imBll.close();
				  		/* Gửi thông tin sang Activity ContactActivity */
				  		intentResultStatus.putExtra("new_id", contactRes.getiId());
				  		setResult(RESULT_OK, intentResultStatus);
			  		}
			  		finish();
		  		}
		  		else
		  		{
	  				Toast.makeText(getApplicationContext(), "Danh bạ với tên " + contactAdd.getsFirstname() + contactAdd.getsLastname() + " đã tồn tại!", Toast.LENGTH_LONG).show();
		  		}
		  		//Đóng kết nối
		  		contactBll.close();
		  	}
		  });
	        btnCancel.setOnClickListener(new OnClickListener() {
		  	@Override
		  	public void onClick(View v) {
		  		Intent resultIntent = new Intent();
		  	    setResult(Activity.RESULT_CANCELED, resultIntent);
		  	    finish();
		  	}
		  });
        }catch (Exception e) {
        	Toast.makeText(getApplicationContext(), "Lỗi: " + e.getMessage(), Toast.LENGTH_LONG);
		}
    }
}
