//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.11.27 a las 12:19:13 AM COT 
//


package com.parameta.empleado_ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EmpleadoInfo" type="{http://parameta.com/empleado-ws}EmpleadoInfo"/>
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
    "empleadoInfo"
})
@XmlRootElement(name = "actualizarEmpleadoRequest")
public class ActualizarEmpleadoRequest {

    @XmlElement(name = "EmpleadoInfo", required = true)
    protected EmpleadoInfo empleadoInfo;

    /**
     * Obtiene el valor de la propiedad empleadoInfo.
     * 
     * @return
     *     possible object is
     *     {@link EmpleadoInfo }
     *     
     */
    public EmpleadoInfo getEmpleadoInfo() {
        return empleadoInfo;
    }

    /**
     * Define el valor de la propiedad empleadoInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link EmpleadoInfo }
     *     
     */
    public void setEmpleadoInfo(EmpleadoInfo value) {
        this.empleadoInfo = value;
    }

}
