
CREATE TABLE if not exists estado ( 
 id bigserial    ,
  uf character varying     ,
  nome character varying     ,
  ativo boolean     ,
 CONSTRAINT pk_estado PRIMARY KEY (id)    
 );


CREATE TABLE if not exists cidade ( 
 id bigserial    ,
  nome character varying     ,
  cep bigint    ,
 estado_id  bigint    ,
  ativo boolean     ,
 CONSTRAINT pk_cidade PRIMARY KEY (id) , 
 CONSTRAINT fk_estado FOREIGN KEY (estado_id) REFERENCES estado  (id)   
 );

CREATE TABLE if not exists pessoa_juridica ( 
 id bigserial    ,
  razao_soc character varying     ,
  nome_fan character varying     ,
  cnpj bigint    ,
  logradouro character varying     ,
  bairro character varying     ,
  numero bigint    ,
  complemento character varying     ,
  ativo boolean     ,
 cidade_id  bigint    ,
  dt_adesao  date     ,
 CONSTRAINT pk_pessoa_juridica PRIMARY KEY (id) 
, CONSTRAINT fk_cidade FOREIGN KEY (cidade_id) REFERENCES cidade  (id)   
 );

