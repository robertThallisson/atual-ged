
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


CREATE TABLE if not exists permissao ( 
 id bigserial    ,
  descricao character varying     ,
  ativo boolean     ,
 CONSTRAINT pk_permissao PRIMARY KEY (id)    
 );


CREATE TABLE if not exists pessoa_juridica ( 
 id bigserial    ,
  razao_social character varying     ,
  nome_fantasia character varying     ,
  cnpj bigint    ,
  logradouro character varying     ,
  bairro character varying     ,
  numero bigint    ,
  complemento character varying     ,
  ativo boolean     ,
 cidade_id  bigint    ,
  data_adessao  date     ,
 CONSTRAINT pk_pessoa_juridica PRIMARY KEY (id) 
, CONSTRAINT fk_cidade FOREIGN KEY (cidade_id) REFERENCES cidade  (id)   
 );


CREATE TABLE if not exists perfil_usuario ( 
 id bigserial    ,
  nome character varying     ,
 empresa_id  bigint    ,
 CONSTRAINT pk_perfil_usuario PRIMARY KEY (id) 
--, CONSTRAINT fk_empresa FOREIGN KEY (empresa_id) REFERENCES empresa  (id)   
 );


 CREATE TABLE if not exists usuario ( 
 id bigserial    ,
  login character varying     ,
  email character varying     ,
  senha character varying     ,
  data_bloqueio  date     ,
  ativo boolean     ,
 pessoa_juridica_id  bigint    ,
 perfil_usuario_id  bigint    ,
 CONSTRAINT pk_usuario PRIMARY KEY (id) 
, CONSTRAINT fk_pessoa_juridica FOREIGN KEY (pessoa_juridica_id) REFERENCES pessoa_juridica  (id)
, CONSTRAINT fk_perfil_usuario FOREIGN KEY (perfil_usuario_id) REFERENCES perfil_usuario  (id)   
 );

