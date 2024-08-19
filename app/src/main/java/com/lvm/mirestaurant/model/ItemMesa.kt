package com.lvm.mirestaurant.model

data class ItemMesa(val itemMenu: ItemMenu, var cantidad: Int) {
    fun calcularSubtotal(): Int {
        return cantidad * itemMenu.precio
    }
}