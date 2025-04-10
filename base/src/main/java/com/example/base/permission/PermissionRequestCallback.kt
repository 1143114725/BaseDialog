package com.example.base.permission


interface PermissionRequestCallback {
    /**
     * Callback for the request result.
     * @param allGranted
     *          Indicate if all permissions that are granted.
     * @param grantedList
     *          All permissions that granted by user.
     * @param deniedList
     *          All permissions that denied by user.
     */
    fun onResult(allGranted: Boolean, grantedList:List<String> , deniedList:List<String>)

}