package com.cfeindia.b2bserviceapp.apps.thirdparty.momapi;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BillAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ClientTransactionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrentBalance" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="KNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MMPLTransactionID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OperatorTransactionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ResponseAction" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ResponseMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StatusCode" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "billAmount",
    "clientTransactionID",
    "currentBalance",
    "kNumber",
    "mmplTransactionID",
    "operatorTransactionID",
    "responseAction",
    "responseMessage",
    "statusCode"
})
@XmlRootElement(name = "BillObjects.DHBVNBillPaymentResult")
public class BillObjectsDHBVNBillPaymentResult {

    @XmlElement(name = "BillAmount", required = true, nillable = true)
    protected String billAmount;
    @XmlElement(name = "ClientTransactionID", required = true, nillable = true)
    protected String clientTransactionID;
    @XmlElement(name = "CurrentBalance")
    protected Double currentBalance;
    @XmlElement(name = "KNumber", required = true, nillable = true)
    protected String kNumber;
    @XmlElement(name = "MMPLTransactionID")
    protected Integer mmplTransactionID;
    @XmlElement(name = "OperatorTransactionID", required = true)
    protected String operatorTransactionID;
    @XmlElement(name = "ResponseAction", required = true)
    protected String responseAction;
    @XmlElement(name = "ResponseMessage", required = true)
    protected String responseMessage;
    @XmlElement(name = "StatusCode")
    protected Integer statusCode;

    /**
     * Gets the value of the billAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillAmount() {
        return billAmount;
    }

    /**
     * Sets the value of the billAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillAmount(String value) {
        this.billAmount = value;
    }

    /**
     * Gets the value of the clientTransactionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientTransactionID() {
        return clientTransactionID;
    }

    /**
     * Sets the value of the clientTransactionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientTransactionID(String value) {
        this.clientTransactionID = value;
    }

    /**
     * Gets the value of the currentBalance property.
     * 
     */
    public Double getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Sets the value of the currentBalance property.
     * 
     */
    public void setCurrentBalance(Double value) {
        this.currentBalance = value;
    }

    /**
     * Gets the value of the kNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKNumber() {
        return kNumber;
    }

    /**
     * Sets the value of the kNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKNumber(String value) {
        this.kNumber = value;
    }

    /**
     * Gets the value of the mmplTransactionID property.
     * 
     */
    public Integer getMMPLTransactionID() {
        return mmplTransactionID;
    }

    /**
     * Sets the value of the mmplTransactionID property.
     * 
     */
    public void setMMPLTransactionID(Integer value) {
        this.mmplTransactionID = value;
    }

    /**
     * Gets the value of the operatorTransactionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorTransactionID() {
        return operatorTransactionID;
    }

    /**
     * Sets the value of the operatorTransactionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorTransactionID(String value) {
        this.operatorTransactionID = value;
    }

    /**
     * Gets the value of the responseAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseAction() {
        return responseAction;
    }

    /**
     * Sets the value of the responseAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseAction(String value) {
        this.responseAction = value;
    }

    /**
     * Gets the value of the responseMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Sets the value of the responseMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseMessage(String value) {
        this.responseMessage = value;
    }

    /**
     * Gets the value of the statusCode property.
     * 
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     */
    public void setStatusCode(Integer value) {
        this.statusCode = value;
    }

}
