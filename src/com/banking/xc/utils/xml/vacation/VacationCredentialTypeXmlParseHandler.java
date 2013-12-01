package com.banking.xc.utils.xml.vacation;

import java.io.InputStream;

import com.banking.xc.utils.xml.frame.XmlParseHandler;

public class VacationCredentialTypeXmlParseHandler extends XmlParseHandler {

	public VacationCredentialTypeXmlParseHandler(com.banking.xc.utils.xml.frame.XmlParseListener XmlParseListener, InputStream inputStream) {
		super(XmlParseListener, inputStream);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cancelParse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public Object getObjectWhenEnd() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * <VacationCredentialTypeResponse>
    <CredentialList>
      <CredentialType>
        <CredentialID>1</CredentialID>
        <CredentialName>身份证</CredentialName>
      </CredentialType>
      <CredentialType>
        <CredentialID>2</CredentialID>
        <CredentialName>护照</CredentialName>
      </CredentialType>
    </CredentialList>
  </VacationCredentialTypeResponse>

	 */
}
