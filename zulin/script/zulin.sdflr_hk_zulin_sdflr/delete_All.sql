DELETE FROM pub_bcr_candiattr WHERE pk_nbcr = '0001ZZ100000000WSLVZ';
DELETE FROM pub_bcr_elem WHERE pk_billcodebase in ( select pk_billcodebase from pub_bcr_RuleBase where nbcrcode = 'HK40' );
DELETE FROM pub_bcr_RuleBase WHERE nbcrcode = 'HK40';
DELETE FROM pub_bcr_nbcr WHERE pk_nbcr = '0001ZZ100000000WSLVZ';
DELETE FROM pub_bcr_OrgRela WHERE pk_billcodebase = '0001ZZ100000000WSLW0';
DELETE FROM pub_bcr_RuleBase WHERE pk_billcodebase = '0001ZZ100000000WSLW0';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ100000000WSLW1';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ100000000WSLW2';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ100000000WSLW3';
DELETE FROM bd_billtype2 WHERE pk_billtypeid = '0001ZZ100000000WSLVM';
DELETE FROM bd_fwdbilltype WHERE pk_billtypeid = '0001ZZ100000000WSLVM';
DELETE FROM pub_function WHERE pk_billtype = 'HK40';
DELETE FROM pub_billaction WHERE pk_billtypeid = '0001ZZ100000000WSLVM';
DELETE FROM pub_billactiongroup WHERE pk_billtype = 'HK40';
DELETE FROM bd_billtype WHERE pk_billtypeid = '0001ZZ100000000WSLVM';
delete from temppkts;
DELETE FROM sm_rule_type WHERE pk_rule_type = null;
DELETE FROM sm_permission_res WHERE pk_permission_res = null;
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ100000000WSLVN';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ100000000WSLVO';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ100000000WSLVP';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ100000000WSLVQ';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ100000000WSLVR';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ100000000WSLVS';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ100000000WSLVT';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ100000000WSLVU';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ100000000WSLVV';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ100000000WSLVW';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ100000000WSLVX';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ100000000WSLVY';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ100000000WSLVL';
delete from pub_print_datasource where ctemplateid = '0001ZZ100000000WSLNC';
delete from pub_print_cell where ctemplateid = '0001ZZ100000000WSLNC';
delete from pub_print_line where ctemplateid = '0001ZZ100000000WSLNC';
delete from pub_print_variable where ctemplateid = '0001ZZ100000000WSLNC';
delete from pub_print_template where ctemplateid = '0001ZZ100000000WSLNC';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ100000000WSLNB';
delete from pub_query_condition where pk_templet = '0001ZZ100000000WSLM6';
delete from pub_query_templet where id = '0001ZZ100000000WSLM6';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ100000000WSLM5';
delete from pub_billtemplet_b where pk_billtemplet = '0001ZZ100000000WSLJO';
delete from pub_billtemplet where pk_billtemplet = '0001ZZ100000000WSLJO';
DELETE FROM pub_billtemplet_t WHERE pk_billtemplet = '0001ZZ100000000WSLJO';
DELETE FROM sm_menuitemreg WHERE pk_menuitem = '0001ZZ100000000WSLJN';
DELETE FROM sm_funcregister WHERE cfunid = '0001ZZ100000000WSLJL';
DELETE FROM sm_paramregister WHERE pk_param = '0001ZZ100000000WSLJM';
