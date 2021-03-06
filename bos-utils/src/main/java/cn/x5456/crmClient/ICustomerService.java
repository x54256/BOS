
package cn.x5456.crmClient;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ICustomerService", targetNamespace = "http://service.x5456.cn/")
@XmlSeeAlso({
//    ObjectFactory.class
})
public interface ICustomerService {


    /**
     *
     * @return
     *     returns java.util.List<cn.x5456.service.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://service.x5456.cn/", className = "cn.x5456.service.FindAll")
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://service.x5456.cn/", className = "cn.x5456.service.FindAllResponse")
    public List<Customer> findAll();

    /**
     *
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findDecidedzoneIdByAddress", targetNamespace = "http://service.x5456.cn/", className = "cn.x5456.service.FindDecidedzoneIdByAddress")
    @ResponseWrapper(localName = "findDecidedzoneIdByAddressResponse", targetNamespace = "http://service.x5456.cn/", className = "cn.x5456.service.FindDecidedzoneIdByAddressResponse")
    public String findDecidedzoneIdByAddress(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<cn.x5456.service.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findListHasAssociation", targetNamespace = "http://service.x5456.cn/", className = "cn.x5456.service.FindListHasAssociation")
    @ResponseWrapper(localName = "findListHasAssociationResponse", targetNamespace = "http://service.x5456.cn/", className = "cn.x5456.service.FindListHasAssociationResponse")
    public List<Customer> findListHasAssociation(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     * @param arg0
     * @return returns cn.x5456.service.Customer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findCustomerByTelephone", targetNamespace = "http://service.x5456.cn/", className = "cn.x5456.service.FindCustomerByTelephone")
    @ResponseWrapper(localName = "findCustomerByTelephoneResponse", targetNamespace = "http://service.x5456.cn/", className = "cn.x5456.service.FindCustomerByTelephoneResponse")
    public Customer findCustomerByTelephone(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "updateAssociation", targetNamespace = "http://service.x5456.cn/", className = "cn.x5456.service.UpdateAssociation")
    @ResponseWrapper(localName = "updateAssociationResponse", targetNamespace = "http://service.x5456.cn/", className = "cn.x5456.service.UpdateAssociationResponse")
    public void updateAssociation(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    List<Integer> arg1);

    /**
     * @return returns java.util.List<cn.x5456.service.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findListNotAssociation", targetNamespace = "http://service.x5456.cn/", className = "cn.x5456.service.FindListNotAssociation")
    @ResponseWrapper(localName = "findListNotAssociationResponse", targetNamespace = "http://service.x5456.cn/", className = "cn.x5456.service.FindListNotAssociationResponse")
    public List<Customer> findListNotAssociation();

}
