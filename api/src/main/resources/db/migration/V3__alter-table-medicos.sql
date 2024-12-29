alter table medicos add ativo TINYINT(1);
update medicos set ativo = 1;
alter table medicos modify ativo TINYINT(1) NOT NULL;
