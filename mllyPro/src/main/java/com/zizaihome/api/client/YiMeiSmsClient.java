package com.zizaihome.api.client;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import cn.b2m.eucp.sdkhttp.Mo;
import cn.b2m.eucp.sdkhttp.SDKServiceBindingStub;
import cn.b2m.eucp.sdkhttp.SDKServiceLocator;
import cn.b2m.eucp.sdkhttp.StatusReport;

public class YiMeiSmsClient {
	private String softwareSerialNo = "8SDK-EMY-6699-RISTT";
	private String key = "711589";

	public YiMeiSmsClient(String sn, String key) {
		this.softwareSerialNo = sn;
		this.key = key;
		init();
	}
	
	public YiMeiSmsClient() {
		init();
	}

	SDKServiceBindingStub binding;

	public void init() {
		try {
			binding = (SDKServiceBindingStub) new SDKServiceLocator()
					.getSDKService();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
		}
	}

	public int chargeUp(String cardNo, String cardPass) throws RemoteException {
		int value = -1;
		value = binding.chargeUp(softwareSerialNo, key, cardNo, cardPass);
		return value;
	}

	public double getBalance() throws RemoteException {
		double value = 0.0;
		value = binding.getBalance(softwareSerialNo, key);
		return value;
	}

	public double getEachFee() throws RemoteException {
		double value = 0.0;
		value = binding.getEachFee(softwareSerialNo, key);
		return value;
	}

	public List<Mo> getMO() throws RemoteException {
		Mo[] mo = binding.getMO(softwareSerialNo, key);

		if (null == mo) {
			return null;
		} else {
			List<Mo> molist = Arrays.asList(mo);
			return molist;
		}
	}

	public List<StatusReport> getReport() throws RemoteException {
		StatusReport[] sr = binding.getReport(softwareSerialNo, key);
		if (null != sr) {
			return Arrays.asList(sr);
		} else {
			return null;
		}
	}

	public int logout() throws RemoteException {
		int value = -1;
		value = binding.logout(softwareSerialNo, key);
		return value;
	}

	public int registDetailInfo(String eName, String linkMan, String phoneNum,
			String mobile, String email, String fax, String address,
			String postcode) throws RemoteException {
		int value = -1;
		value = binding.registDetailInfo(softwareSerialNo, key, eName, linkMan,
				phoneNum, mobile, email, fax, address, postcode);
		return value;
	}

	public int registEx(String password) throws RemoteException {
		int value = -1;
		value = binding.registEx(softwareSerialNo, key, password);
		return value;
	}

	public int sendSMS(String[] mobiles, String smsContent, String addSerial,
			int smsPriority) throws RemoteException {
		int value = -1;
		value = binding.sendSMS(softwareSerialNo, key, "", mobiles, "【自在家平台】"+smsContent,
				addSerial, "gbk", smsPriority, 0);
		return value;
	}

	public int sendScheduledSMSEx(String[] mobiles, String smsContent,
			String sendTime, String srcCharset) throws RemoteException {
		int value = -1;
		value = binding.sendSMS(softwareSerialNo, key, sendTime, mobiles,
				"【自在家平台】"+smsContent, "", srcCharset, 3, 0);
		return value;
	}

	public int sendSMSEx(String[] mobiles, String smsContent, String addSerial,
			String srcCharset, int smsPriority, long smsID)
			throws RemoteException {
		int value = -1;
		value = binding.sendSMS(softwareSerialNo, key, "", mobiles, "【自在家平台】"+smsContent,
				addSerial, srcCharset, smsPriority, smsID);
		return value;
	}

	public String sendVoice(String[] mobiles, String smsContent,
			String addSerial, String srcCharset, int smsPriority, long smsID)
			throws RemoteException {
		String value = null;
		value = binding.sendVoice(softwareSerialNo, key, "", mobiles,
				smsContent, addSerial, srcCharset, smsPriority, smsID);
		return value;
	}

	public int serialPwdUpd(String serialPwd, String serialPwdNew)
			throws RemoteException {
		int value = -1;
		value = binding.serialPwdUpd(softwareSerialNo, key, serialPwd,
				serialPwdNew);
		return value;
	}
	
	
	public static void main(String args[]){
		YiMeiSmsClient yimei = new YiMeiSmsClient();
		String[] mobiles = {"13427818326"};
		try {
			yimei.sendSMS(mobiles,"测试",null,0);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
