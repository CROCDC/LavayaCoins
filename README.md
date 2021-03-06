Lavaya Coins
==============================


### Objetivo de la app
el objetivo de esta app es ser una base de datos que enlista todos lo lugares de venta de monedas de la empresa
Lavaya (por si no conocen a la empresa lavaya https://lavaya.com/)


### Text coverage

[![](https://i.postimg.cc/MpYLWdxY/Sin-ti-tulo-6.png)](https://postimg.cc/qNzwwcNt)

#### Demo
una demostracion de la app esta hosteada en heroku(https://lavayacoins.herokuapp.com/graphiql),
Heroku apaga el servidor cuando deja de recibir trafico por una hora asi q puede tardar un poco en levantar.
A continuar voy a dejar queries y mutations para poder probar la demo:

#### Tiendas

###### [findAllStores](https://lavayacoins.herokuapp.com/graphiql?query=%7B%0A%20%20findAllStores%20%7B%0A%20%20%20%20id%0A%20%20%20%20coordinates%20%7B%0A%20%20%20%20%20%20latitude%0A%20%20%20%20%20%20longitude%0A%20%20%20%20%7D%0A%20%20%7D%0A%7D%0A)

###### [saveStore](https://lavayacoins.herokuapp.com/graphiql?query=mutation%7B%0A%20%20saveStore(input%3A%7B%0A%20%20%20%20accessToken%3A%22eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdG9yZXMiLCJleHAiOjE2MTg1NDMwNTUsImF1dGhvcml0aWVzIjpbIkFETUlOX1NUT1JFUyJdfQ.HI7vq9Kce5MS1szPHO2cua7kumJKSfn802EQ0AAwcSvvok-bX_BfbiOL-4C7zjITveLDbmMShrN89NwHqCL5NQ%22%0A%20%20%20%20store%3A%7B%0A%20%20%20%20%20%20name%3A%22Ciudad%20De%20La%20paz%20151%22%2C%0A%20%20%20%20%20%20coordinatesInput%3A%7B%0A%20%20%20%20%20%20%20%20latitude%3A-34.5811182%2C%0A%20%20%20%20%20%20%20%20longitude%3A-58.4287506%0A%20%20%20%20%20%20%7D%0A%20%20%20%20%7D%0A%20%20%7D)%7B%0A%20%20%20%20...%20on%20SaveStoreSuccess%7B%0A%20%20%20%20%20%20store%7B%0A%20%20%20%20%20%20%20%20name%0A%20%20%20%20%20%20%20%20coordinates%7B%0A%20%20%20%20%20%20%20%20%20%20latitude%0A%20%20%20%20%20%20%20%20%20%20longitude%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%7D%0A%20%20%20%20%7D%0A%20%20%7D%0A%7D%0A%0A)

###### [deleteStoreById](https://lavayacoins.herokuapp.com/graphiql?query=mutation%7B%0A%20%20deleteStoreById(input%3A%7B%0A%20%20%20%20id%3A%22402880fc775e60ba01775e64531e0000%22%2C%0A%20%20%20%20accessToken%3A%22eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdG9yZXMiLCJleHAiOjE2MTg1NDQyNDEsImF1dGhvcml0aWVzIjpbIkFETUlOX1NUT1JFUyJdfQ.i1vzZqHmAvVbIEKypPyRxN5WCBPPjk2nKhpc8yHiLOIn--lu43_ZiL6icRl22TbnvysiMnQP3Bn0hjjOqNnqyQ%22%0A%20%20%7D)%20%7B%0A%20%20%20...%20on%20DeleteStoreSuccess%7B%0A%20%20%20%20store%7B%0A%20%20%20%20%20%20id%0A%20%20%20%20%7D%0A%20%20%7D%0A%20%20%7D%0A%7D%0A)

#### Usuarios

datos de Usuarios 

admin con authority CREATE_ADMINS

username:"admin"
password:"admin"

admin con authority ADMIN_STORES

username:"stores"
password:"stores"

customer

username:"customer"
password:"customer"

##### [createCustomerUser](https://lavayacoins.herokuapp.com/graphiql?query=mutation%7B%0A%20%20createCustomerUser(input%3A%7B%0A%20%20%20%20username%3A%22TEST%22%0A%20%20%20%20password%3A%22TEST%22%0A%20%20%7D)%7B%0A%20%20%20%20...%20on%20CreateCustomerSuccess%7B%0A%20%20%20%20%20%20user%7B%0A%20%20%20%20%20%20%20%20username%0A%20%20%20%20%20%20%20%20password%0A%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20credentials%7B%0A%20%20%20%20%20%20%20%20accessToken%0A%20%20%20%20%20%20%7D%0A%20%20%20%20%7D%0A%20%20%7D%0A%7D%0A)

###### [createAdminUser](https://lavayacoins.herokuapp.com/graphiql?query=mutation%7B%0A%20%20createAdminUser(input%3A%7B%0A%20%20%20%20accessToken%3A%22eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxODU0NTk3OCwiYXV0aG9yaXRpZXMiOlsiQ1JFQVRFX0FETUlOUyJdfQ.P16oIFn8Y3I4IJJTTeG0RrEQ6iMAIFTPf8q1nw0MOtKkrj6ByFDX04y7sPjp6-Y_sn49rkdIZ8hV9aTPKQkbVQ%22%0A%20%20%20%20username%3A%22TEST%22%0A%20%20%20%20password%3A%22TEST%22%0A%20%20%7D)%7B%0A%20%20%20%20...%20on%20CreateAdminSuccess%7B%0A%20%20%20%20%20%20user%7B%0A%20%20%20%20%20%20%20%20username%0A%20%20%20%20%20%20%20%20password%0A%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20credentials%7B%0A%20%20%20%20%20%20%20%20accessToken%0A%20%20%20%20%20%20%7D%0A%20%20%20%20%7D%0A%20%20%7D%0A%7D%0A)

###### [loginAdminUser](https://lavayacoins.herokuapp.com/graphiql?query=mutation%7B%0A%20%20loginAdminUser(input%3A%7B%0A%20%20%20%20username%3A%22stores%22%0A%20%20%20%20password%3A%22stores%22%0A%20%20%7D)%7B%0A%20%20%20%20...%20on%20LoginAdminSuccess%7B%0A%20%20%20%20%20%20user%7B%0A%20%20%20%20%20%20%20%20username%0A%20%20%20%20%20%20%20%20password%0A%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20credentials%7B%0A%20%20%20%20%20%20%20%20accessToken%0A%20%20%20%20%20%20%7D%0A%20%20%20%20%7D%0A%20%20%7D%0A%7D%0A)

##### [loginCustomerUser](https://lavayacoins.herokuapp.com/graphiql?query=mutation%7B%0A%20%20loginCustomerUser(input%3A%7B%0A%20%20%20%20username%3A%22customer%22%0A%20%20%20%20password%3A%22customer%22%0A%20%20%7D)%7B%0A%20%20%20%20...%20on%20LoginCustomerSuccess%7B%0A%20%20%20%20%20%20user%7B%0A%20%20%20%20%20%20%20%20username%0A%20%20%20%20%20%20%20%20password%0A%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20credentials%7B%0A%20%20%20%20%20%20%20%20accessToken%0A%20%20%20%20%20%20%7D%0A%20%20%20%20%7D%0A%20%20%7D%0A%7D%0A)

#### Explicaci??n de la app

##### Tiendas
en esta secci??n se explicar?? los distintos m??todos que permiten modificar la informaci??n de las tiendas

###### findAllStores
este m??todo expone todas las tiendas guardadas en la base de datos, siempre devolver?? una lista con tiendas o sin tiendas

###### saveStore
este m??todo guarda la tienda recibida en la base de datos siempre y cuando se cumplan una serie de condiciones:

- que el accessToken sea v??lido y contenga la authority ADMIN_STORES, la cual es necesaria para manipular la informaci??n de las tiendas,
en caso de que el accessToken sea invalido devolvera SaveStoreErrorInvalidAuthorities

- en caso de que el accessToken sea v??lido la tienda se guardar?? en la base de datos y se devolver?? SaveStoreSuccess

###### saveStores
este m??todo guarda todas las tiendas recibidas en la base de datos siempre y cuando se cumplan una serie de condiciones:

- que el accessToken sea v??lido y contenga la authority ADMIN_STORES, la cual es necesaria para manipular la informaci??n de las tiendas,
 en caso de el accessToken sea invalido devolvera SaveStoreErrorInvalidAuthorities

- en caso de que el accessToken sea v??lido la tienda se guardar?? en la base de datos y se devolver?? SaveStoresSuccess

###### deleteStoreById
este m??todo permite eliminar una tienda siempre y cuando  se cumplan una serie de condiciones:

- que el accessToken sea v??lido y contenga la authority ADMIN_STORES, la cual es necesaria para manipular la informaci??n de las tiendas,
en caso de el accessToken sea invalido devolvera DeleteStoreInvalidAuthorities

- si la tienda no existe en la base de datos se devolver?? DeleteStoreNotExist

- en caso de que el accessToken sea v??lido y la tienda exista se devolver?? DeleteStoreSuccess

##### Usuarios

###### createAdminUser
este m??todo permite crear un usuario admin con las authorities (ADMIN_STORES, SEND_TIPS) siempre y
cuando se cumplan una serie de condiciones:

- que el accessToken sea v??lido y contenga la authority CREATE_ADMINS, en caso de que el accessToken sea invalido se
devolvera CreateAdminError(CreateAdminErrorCause.INVALID_AUTHORITIES)

- si el usuario existe en la base de datos se devolvera CreateAdminError(CreateAdminErrorCause.USER_ALREADY_EXIST)

- si el usuario no existe en la base de datos se guardar?? y se devolver?? CreateAdminSuccess

###### loginAdminUser
este m??todo permite que un usuario admin se loguee con sus credenciales, siempre y cuando se cumplan una serie
de condiciones:

- si el usuario no existe en la base de datos se devolver?? LoginAdminError(LoginAdminErrorCause.USER_NOT_EXIST)

- que la contrase??a recibida corresponda con el usuario guardado en la base de datos se devolver??
LoginAdminError(LoginAdminErrorCause.PASSWORD_MISMATCH)

- en caso de que el usuario exista y la contrase??a sea v??lida se devolver?? LoginAdminSuccess

###### createCustomerUser
este m??todo permite crear un usuario customer con la authority (SEND_TIPS) siempre y cuando se cumplan una serie de
condiciones

- si el nombre usuario ya existe en la base de datos se devolvera CreateCustomerError(CreateCustomerErrorCause.USER_ALREADY_EXIST)

- en caso de que no exista se devolver?? CreateCustomerSuccess

###### loginCustomerUser
este m??todo permite que un usuario customer se loguee con sus credenciales, siempre y cuando se cumplan una serie
de condiciones:

- si el usuario no existe en la base de datos se devolvera LoginCustomerError(LoginCustomerErrorCause.USER_NOT_EXIST)

- si la contrase??a recibida corresponde con el usuario guardado en la base de datos se devolver??
LoginCustomerError(LoginCustomerErrorCause.PASSWORD_MISMATCH)

- en caso de que el usuario exista y la contrase??a sea v??lida se devolver?? LoginCustomerSuccess