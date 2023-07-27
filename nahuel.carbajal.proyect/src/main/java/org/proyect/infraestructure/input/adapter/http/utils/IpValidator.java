package org.proyect.infraestructure.input.adapter.http.utils;

import jakarta.ws.rs.InternalServerErrorException;
import org.proyect.infraestructure.input.adapter.http.advice.exception.InvalidIpException;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpValidator {
    public static void validateIp(String ipAddress) throws InvalidIpException, InternalServerErrorException {
        try {
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            if (!(inetAddress instanceof java.net.Inet4Address || inetAddress instanceof java.net.Inet6Address)) {
                throw new InvalidIpException("ip is not valid");
            }
        } catch (UnknownHostException e) {
            throw new InvalidIpException(e.getMessage());
        }
    }
}
