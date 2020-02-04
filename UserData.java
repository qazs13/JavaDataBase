package userentry;


public class UserData {
    private int id;
    private String fname;
    private String mname;
    private String lname;
    private String email;
    private String phone;
    
    UserData ()
    {
        
    }
    
    UserData (int id, String fname, String mname,String lname,String email,String phone)
    {
        this.id = id;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
    }
    
    public void setFullData (int id, String fname, String mname,String lname,String email,String phone)
    {
        this.id = id;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
    }
    
    public void setFirstName (String fname)
    {
        this.fname = fname;
    }
    
    public void setMiddletName (String mname)
    {
        this.mname = mname;
    }    
    
    public void setLastName (String lname)
    {
        this.lname = lname;
    }    
    
    public void setEmail (String email)
    {
        this.email = email;
    }    
    
    public void setPhone (String phone)
    {
        this.phone = phone;
    }
    
    public int getId ()
    {
        return id;
    }
    
    public String getFirstName ()
    {
        return fname;
    }

    public String getMiddleName ()
    {
        return mname;
    }

    public String getLastName ()
    {
        return lname;
    }

    public String getEmail ()
    {
        return email;
    }

    public String getPhone ()
    {
        return phone;
    }
}
