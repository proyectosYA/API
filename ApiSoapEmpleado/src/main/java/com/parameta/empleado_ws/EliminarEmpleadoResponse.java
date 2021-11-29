//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.11.27 a las 02:12:25 PM COT 
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
 *         &lt;element name="estadoServicio" type="{http://parameta.com/empleado-ws}estadoServicio"/>
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
    "estadoServicio"
})
@XmlRootElement(name = "eliminarEmpleadoResponse")
public class EliminarEmpleadoResponse {

    @XmlElement(required = true)
    protected EstadoServicio estadoServicio;

    /**
     * Obtiene el valor de la propiedad estadoServicio.
     * 
     * @return
     *     possible object is
     *     {@link EstadoServicio }
     *     
     */
    public EstadoServicio getEstadoServicio() {
        return estadoServicio;
    }

    /**
     * Define el valor de la propiedad estadoServicio.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoServicio }
     *     
     */
    public void setEstadoServicio(EstadoServicio value) {
        this.estadoServicio = value;
    }

}
