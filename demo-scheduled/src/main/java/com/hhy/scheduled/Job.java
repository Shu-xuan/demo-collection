   package com.hhy.scheduled;

   import java.util.Comparator;

   /**
    * <p>
    * 描述: TODO
    * </p>
    * <p>版权所有: &copy;古月HYuan</p>
    *
    * @Author 枢璇
    * @Date 2025/3/25 20:31
    */
   public class Job implements Comparable<Job> {
       private Runnable task;
       private Long startTime;
       private Long delay;

       public Long getDelay() {
           return delay;
       }

       public void setDelay(Long delay) {
           this.delay = delay;
       }

       public Runnable getTask() {
           return task;
       }

       public void setTask(Runnable task) {
           this.task = task;
       }

       public Long getStartTime() {
           return startTime;
       }

       public void setStartTime(Long startTime) {
           this.startTime = startTime;
       }

       @Override
       public int compareTo(Job other) {
           return this.startTime.compareTo(other.startTime);
       }
   }
   