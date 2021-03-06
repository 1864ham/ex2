package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.ex2.entity.Memo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {

  @Autowired
  MemoRepository memoRepository;

  @Test
  public void testClass() {
    System.out.println("testClass::"+memoRepository.getClass().getName());
  }
/*
  @Test
  public void testInsert(){
    IntStream.rangeClosed(1,100).forEach(i -> {
      Memo memo = Memo.builder().memoText("Sample..."+i).build();
      memoRepository.save(memo);
    });
  }
*/

/*    @Test
    public void testSelect(){
        Long mno = 100L;
        Optional<Memo> result = memoRepository.findById(mno);
        System.out.println("======================");
        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        } else {
            System.out.println("존재하지 않는 엔티티입니다.");
        }
    }*/

/*
    @Transactional
    @Test
    public void testSelect2() {
        Long mno = 100L;
        Memo memo = memoRepository.getOne(mno);
        System.out.println("======================");
        System.out.println(memo);
    }
*/

/*
    @Test
    public void testUpdate() {
        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
        System.out.println(memoRepository.save(memo));
    }
*/
/*    @Test
    public void testDelete() {
        Long mno = 100L;
        memoRepository.deleteById(mno);
        testSelect();
    }*/

/*
    @Test
    public void testPageDefault() {
        Pageable pageable = PageRequest.of(9, 10);
        Page<Memo> result = memoRepository.findAll(pageable);
        System.out.println("result >> " + result);
        System.out.println("======================");
        System.out.println("result.getTotalPages() : " + result.getTotalPages());
        System.out.println("result.getTotalElements() : " + result.getTotalElements());
        System.out.println("result.getNumber() : " + result.getNumber());
        System.out.println("result.getSize() : " + result.getSize());
        System.out.println("result.hasNext() : " + result.hasNext());
        System.out.println("result.result() : " + result.isFirst());
        System.out.println("----------------------");
        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }
*/

  @Test
  public void testSort() {
    Sort sort1 = Sort.by("mno").descending();
    Pageable pageable = PageRequest.of(0, 10, sort1);
    Page<Memo> result = memoRepository.findAll(pageable);
    result.get().forEach( memo -> {
      System.out.println(memo);
    });
  }

  @Test
  public void testQueryMethods() {
    List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);

    for (Memo memo : list) {
      System.out.println(memo);
    }
  }
  @Test
  public void testQueryMethodsWithPageable() {
    Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
    Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);
    result.get().forEach(memo -> System.out.println(memo));
  }

 /*   @Commit
    @Transactional
    @Test
    void TestDeleteQueryMethods() {
        memoRepository.deleteByMnoLessThan(20L);
    }*/

  @Test
  void testGetListDesc() {
    memoRepository.getListDesc();
    List<Memo> list = memoRepository.getListDesc();
    for (Memo memo : list) {
      System.out.println(memo);
    }
  }

/*
  @Test
  void testUpdateMemoText() {
    memoRepository.updateMemoText(50L, "Changed2");
  }

  @Test
  void updateMemoObject() {
    Memo memo = Memo.builder().mno(50L).memoText("Changed").build();
    memoRepository.updateMemoObject(memo);
  }
*/

/*  @Test
  void testGetListWithQuery() {
    Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
    Page<Memo> result = memoRepository.getListWithQuery(20L, pageable);
    result.get().forEach(memo -> System.out.println(memo));
  }*/
  @Test
  void testGetListWithQueryObject() {
    Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
    Page<Object[]> result = memoRepository.getListWithQueryObject(20L, pageable);
    result.get().forEach(objects -> System.out.println(
            objects[0]+"/"+objects[1]+"/"+objects[2]));
  }

  @Test
  void testGetNativeResult() {
    List<Memo> result = memoRepository.getNativeResult();
    for (Memo memo : result) {
      System.out.println(memo);
    }

  }
}