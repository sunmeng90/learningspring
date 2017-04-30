create table emp
(
  emp_no int not null
    primary key,
  birth_date date not null,
  first_name varchar(14) not null,
  last_name varchar(16) not null,
  gender enum('M', 'F') not null,
  hire_date date not null,
  password varchar(32) default '1bc29b36f623ba82aaf6724fd3b16718' not null,
  email varchar(100) null
)
;

create table emp_bakup
(
  emp_no int not null
    primary key,
  birth_date date not null,
  first_name varchar(14) not null,
  last_name varchar(16) not null,
  gender enum('M', 'F') not null,
  hire_date date not null,
  password varchar(32) default '1bc29b36f623ba82aaf6724fd3b16718' not null,
  email varchar(100) null
)
;

create procedure getEmpByAgeOldThan (IN age int)
  begin
    select * from emp where emp.birth_date < date_sub(now(), INTERVAL age YEAR) limit 1,10;
  end;

create procedure getPagedEmp (IN startIdx int, IN pageSize int, OUT totalCount int, OUT totalPage int)
  begin

    declare endIdx int;
    /**set total count*/
    SELECT COUNT(1) INTO totalCount from emp;
    /**set page size */
    if pageSize < 0 then
      set pageSize=10;
    end if;
    if startIdx < 0 then
      set startIdx = 0;
    end if;

    if startIdx > totalCount then
      set startIdx = totalCount;
      set endIdx = totalCount;
    end if;

    if(startIdx + pageSize) > totalCount then
      set endIdx = totalCount;
    else
      set endIdx = startIdx + pageSize;
    end if;

    if mod(totalCount, pageSize) = 0 then
      set totalPage = totalCount/pageSize;
    else
      set totalPage = totalCount/pageSize +1;
    end if;
    SELECT
      *
    FROM
      emp
    LIMIT startIdx , endIdx;

  end;

create procedure simpleproc (OUT param1 int)
  BEGIN
    SELECT COUNT(*) INTO param1 FROM t;
  END;

