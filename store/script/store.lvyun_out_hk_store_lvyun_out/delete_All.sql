DELETE FROM pub_bcr_candiattr WHERE pk_nbcr = '0001ZZ100000001P7OHS';
DELETE FROM pub_bcr_elem WHERE pk_billcodebase in ( select pk_billcodebase from pub_bcr_RuleBase where nbcrcode = 'HK46' );
DELETE FROM pub_bcr_RuleBase WHERE nbcrcode = 'HK46';
DELETE FROM pub_bcr_nbcr WHERE pk_nbcr = '0001ZZ100000001P7OHS';
DELETE FROM pub_bcr_OrgRela WHERE pk_billcodebase = '0001ZZ100000001P7OHT';
DELETE FROM pub_bcr_RuleBase WHERE pk_billcodebase = '0001ZZ100000001P7OHT';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ100000001P7OHU';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ100000001P7OHV';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ100000001P7OHW';
DELETE FROM bd_billtype2 WHERE pk_billtypeid = '0001ZZ100000001P7OHF';
DELETE FROM bd_fwdbilltype WHERE pk_billtypeid = '0001ZZ100000001P7OHF';
DELETE FROM pub_function WHERE pk_billtype = 'HK46';
DELETE FROM pub_billaction WHERE pk_billtypeid = '0001ZZ100000001P7OHF';
DELETE FROM pub_billactiongroup WHERE pk_billtype = 'HK46';
DELETE FROM bd_billtype WHERE pk_billtypeid = '0001ZZ100000001P7OHF';
delete from temppkts;
DELETE FROM sm_rule_type WHERE pk_rule_type = null;
DELETE FROM sm_permission_res WHERE pk_permission_res = null;
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ100000001P7OHG';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ100000001P7OHH';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ100000001P7OHI';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ100000001P7OHJ';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ100000001P7OHK';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ100000001P7OHL';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ100000001P7OHM';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ100000001P7OHN';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ100000001P7OHO';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ100000001P7OHP';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ100000001P7OHQ';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ100000001P7OHR';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ100000001P7OHE';
delete from pub_print_datasource where ctemplateid = '0001ZZ100000001P7NYL';
delete from pub_print_cell where ctemplateid = '0001ZZ100000001P7NYL';
delete from pub_print_line where ctemplateid = '0001ZZ100000001P7NYL';
delete from pub_print_variable where ctemplateid = '0001ZZ100000001P7NYL';
delete from pub_print_template where ctemplateid = '0001ZZ100000001P7NYL';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ100000001P7NYK';
delete from pub_query_condition where pk_templet = '0001ZZ100000001P7NXE';
delete from pub_query_templet where id = '0001ZZ100000001P7NXE';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ100000001P7NXD';
delete from pub_billtemplet_b where pk_billtemplet = '0001ZZ100000001P7NSK';
delete from pub_billtemplet where pk_billtemplet = '0001ZZ100000001P7NSK';
DELETE FROM pub_billtemplet_t WHERE pk_billtemplet = '0001ZZ100000001P7NSK';
DELETE FROM sm_menuitemreg WHERE pk_menuitem = '0001ZZ100000001P7NSJ';
DELETE FROM sm_funcregister WHERE cfunid = '0001ZZ100000001P7NSH';
DELETE FROM sm_paramregister WHERE pk_param = '0001ZZ100000001P7NSI';
