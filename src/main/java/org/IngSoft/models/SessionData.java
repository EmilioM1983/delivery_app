package org.IngSoft.models;

import org.IngSoft.models.Enum.Role;

public class SessionData {
    private static User usuario;
    private static Object entidad;
    private static Role rol;

    public static void setSesion(User user, Object entidadAsociada, Role tipo) {
        usuario = user;
        entidad = entidadAsociada;
        rol = tipo;
    }

    public static User getUsuario() {
        return usuario;
    }

    public static Object getEntidad() {
        return entidad;
    }

    public static Role getRol() {
        return rol;
    }

    public static boolean esCliente() {
        return rol == Role.Client;
    }

    public static boolean esRestaurante() {
        return rol == Role.Restaurant;
    }

    public static boolean esDealer() {
        return rol == Role.Dealer;
    }

    public static boolean esAdmin() {
        return rol == Role.Admin;
    }

    public static void cerrarSesion() {
        usuario = null;
        entidad = null;
        rol = null;
    }
}
