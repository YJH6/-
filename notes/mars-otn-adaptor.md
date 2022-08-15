### mars-otn-adaptor

```java
mars-otn-adaptor
|----controller
	|----msotn
		|----LldpController
		|----MsotnBaseController
		|----MsotnEthernetOamController
		|----MsotnSwitchController
		|----MstonMplsTpOamController
		|----RemoteController
	|----otn6200
		|----Otn6200Controller
	|----stcommon
		|----AlarmController
		|----BaseController
		|----CommonController
		|----ConfigController
		|----InventoryController
	|----stotn
		|----otnconfigmib
			|----OtnDoAdaptor
		|----otnperfomancemib
			|----OntPMPDoAdaptor
	|----PublicController
	|----TestController
|----model
	|----constant //每个OID是怎么一步一步从根到叶的拼接
		|----CommonOIDConstant  //共同的
		|----MsotnOIDConstant //MSOTN
		|----Otn6200OIDConstant //OTN6200
		|----OtnOIDConstant //OTN
		|----PublicOIDConstant //公开的
	|----enums //枚举，枚举出上面OID的值加上类型
		|----CommonOIDEnum
		|----MsotnOIDEnum
		|----Otn6200Enum
		|----OtnOIDEnim
		|----PublicOIDEnum
|----util
|----Application
```

get/-----/ids：

```java
@PostMapping("/get/global-config/ids")
public R<List<String>> getLldpGlobalConfigIds(@RequestBody RequestMo body){
    String baseIndex = body.getComboIndex() == null ? "" : body.getComboIndex();
    //
    String oid = MsotnOIDConstant.stLldpGlobalRowStatus + baseIndex;
    return R.success(GlobalUtils.getIdsByOid(body.getSnmpNode(), oid));
}
```

