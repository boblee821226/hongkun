DELETE FROM pub_bcr_candiattr WHERE pk_nbcr = '0001ZZ1000000019HDM7';
DELETE FROM pub_bcr_elem WHERE pk_billcodebase in ( select pk_billcodebase from pub_bcr_RuleBase where nbcrcode = 'HK43' );
DELETE FROM pub_bcr_RuleBase WHERE nbcrcode = 'HK43';
DELETE FROM pub_bcr_nbcr WHERE pk_nbcr = '0001ZZ1000000019HDM7';
DELETE FROM pub_bcr_OrgRela WHERE pk_billcodebase = '0001ZZ1000000019HDM8';
DELETE FROM pub_bcr_RuleBase WHERE pk_billcodebase = '0001ZZ1000000019HDM8';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ1000000019HDM9';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ1000000019HDMA';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ1000000019HDMB';
DELETE FROM bd_billtype2 WHERE pk_billtypeid = '0001ZZ1000000019HDLU';
DELETE FROM bd_fwdbilltype WHERE pk_billtypeid = '0001ZZ1000000019HDLU';
DELETE FROM pub_function WHERE pk_billtype = 'HK43';
DELETE FROM pub_billaction WHERE pk_billtypeid = '0001ZZ1000000019HDLU';
DELETE FROM pub_billactiongroup WHERE pk_billtype = 'HK43';
DELETE FROM bd_billtype WHERE pk_billtypeid = '0001ZZ1000000019HDLU';
delete from temppkts;
DELETE FROM sm_rule_type WHERE pk_rule_type = null;
DELETE FROM sm_permission_res WHERE pk_permission_res = null;
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ1000000019HDLV';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ1000000019HDLW';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ1000000019HDLX';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ1000000019HDLY';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ1000000019HDLZ';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ1000000019HDM0';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ1000000019HDM1';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ1000000019HDM2';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ1000000019HDM3';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ1000000019HDM4';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ1000000019HDM5';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ1000000019HDM6';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ1000000019HDLT';
delete from pub_print_datasource where ctemplateid = '0001ZZ1000000019HDAW';
delete from pub_print_cell where ctemplateid = '0001ZZ1000000019HDAW';
delete from pub_print_line where ctemplateid = '0001ZZ1000000019HDAW';
delete from pub_print_variable where ctemplateid = '0001ZZ1000000019HDAW';
delete from pub_print_template where ctemplateid = '0001ZZ1000000019HDAW';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ1000000019HDAV';
delete from pub_query_condition where pk_templet = '0001ZZ1000000019HD9Y';
delete from pub_query_templet where id = '0001ZZ1000000019HD9Y';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ1000000019HD9X';
delete from pub_billtemplet_b where pk_billtemplet = '0001ZZ1000000019HD7W';
delete from pub_billtemplet where pk_billtemplet = '0001ZZ1000000019HD7W';
DELETE FROM pub_billtemplet_t WHERE pk_billtemplet = '0001ZZ1000000019HD7W';
DELETE FROM sm_menuitemreg WHERE pk_menuitem = '0001ZZ1000000019HD7V';
DELETE FROM sm_funcregister WHERE cfunid = '0001ZZ1000000019HD7T';
DELETE FROM sm_paramregister WHERE pk_param = '0001ZZ1000000019HD7U';
