package in.yousee.yousee.model;

public class SessionData
{
	private String sessionId;
	private String userId;
	private String userType;
	private String username;
	private String succesFlag;
	public String getSessionId()
	{
		return sessionId;
	}
	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	} 
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public String getUserType()
	{
		return userType;
	}
	public void setUserType(String userType)
	{
		this.userType = userType;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public void setSuccesFlag(String succesFlag) {
		// TODO Auto-generated method stub
		this.succesFlag = succesFlag;
	}
	public String getSuccesFlag()
	{
		return succesFlag;
	}
	
}
