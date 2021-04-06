Lavaya Coins
==============================


### Objetivo de la app

el objetivo de esta app es ser una base de datos que enlista todos lo lugares de venta de monedas de la empresa
Lavaya (por si no conocen a la empresa lavaya https://lavaya.com/)


#### Demo



#### Explicación de la app

##### Tiendas
en esta sección se explicará los distintos métodos que permiten modificar la información de las tiendas

###### findAllStores
este método expone todas las tiendas guardadas en la base de datos, siempre devolverá una lista con tiendas o sin tiendas

###### saveStore
este método guarda la tienda recibida en la base de datos siempre y cuando se cumplan una serie de condiciones:

- que el accessToken sea válido y contenga la authority ADMIN_STORES, la cual es necesaria para manipular la información de las tiendas,
en caso de que el accessToken sea invalido devolvera SaveStoreErrorInvalidAuthorities

- en caso de que el accessToken sea válido la tienda se guardará en la base de datos y se devolverá SaveStoreSuccess

###### saveStores
este método guarda todas las tiendas recibidas en la base de datos siempre y cuando se cumplan una serie de condiciones:

- que el accessToken sea válido y contenga la authority ADMIN_STORES, la cual es necesaria para manipular la información de las tiendas,
 en caso de el accessToken sea invalido devolvera SaveStoreErrorInvalidAuthorities

- en caso de que el accessToken sea válido la tienda se guardará en la base de datos y se devolverá SaveStoresSuccess

###### deleteStoreById
este método permite eliminar una tienda siempre y cuando  se cumplan una serie de condiciones:

- que el accessToken sea válido y contenga la authority ADMIN_STORES, la cual es necesaria para manipular la información de las tiendas,
en caso de el accessToken sea invalido devolvera DeleteStoreInvalidAuthorities

- si la tienda no existe en la base de datos se devolverá DeleteStoreNotExist

- en caso de que el accessToken sea válido y la tienda exista se devolverá DeleteStoreSuccess

##### Usuarios


###### createAdminUser
este método permite crear un usuario admin con las authorities (ADMIN_STORES, SEND_TIPS) siempre y
cuando se cumplan una serie de condiciones:

- que el accessToken sea válido y contenga la authority CREATE_ADMINS, en caso de que el accessToken sea invalido se
devolvera CreateAdminError(CreateAdminErrorCause.INVALID_AUTHORITIES)

- si el usuario existe en la base de datos se devolvera CreateAdminError(CreateAdminErrorCause.USER_ALREADY_EXIST)

- si el usuario no existe en la base de datos se guardará y se devolverá CreateAdminSuccess

###### loginAdminUser
este método permite que un usuario admin se loguee con sus credenciales, siempre y cuando se cumplan una serie
de condiciones:

- si el usuario no existe en la base de datos se devolverá LoginAdminError(LoginAdminErrorCause.USER_NOT_EXIST)

- que la contraseña recibida corresponda con el usuario guardado en la base de datos se devolverá
LoginAdminError(LoginAdminErrorCause.PASSWORD_MISMATCH)

- en caso de que el usuario exista y la contraseña sea válida se devolverá LoginAdminSuccess

###### createCustomerUser
este método permite crear un usuario customer con la authority (SEND_TIPS) siempre y cuando se cumplan una serie de
condiciones

- si el nombre usuario ya existe en la base de datos se devolvera CreateCustomerError(CreateCustomerErrorCause.USER_ALREADY_EXIST)

- en caso de que no exista se devolverá CreateCustomerSuccess

###### loginCustomerUser
este método permite que un usuario customer se loguee con sus credenciales, siempre y cuando se cumplan una serie
de condiciones:

- si el usuario no existe en la base de datos se devolvera LoginCustomerError(LoginCustomerErrorCause.USER_NOT_EXIST)

- si la contraseña recibida corresponde con el usuario guardado en la base de datos se devolverá
LoginCustomerError(LoginCustomerErrorCause.PASSWORD_MISMATCH)

- en caso de que el usuario exista y la contraseña sea válida se devolverá LoginCustomerSuccess